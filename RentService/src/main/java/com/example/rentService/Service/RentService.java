package com.example.rentService.Service;
import brave.Tracer;
import com.example.rentService.Client.CompanyClient;
import com.example.rentService.Client.ItemClient;
import com.example.rentService.Domain.Enum.Status;
import com.example.rentService.Domain.Rent;
import com.example.rentService.Dto.CompanyDto;
import com.example.rentService.Dto.ItemDto;
import com.example.rentService.Dto.RentDto;
import com.example.rentService.Dto.RentProducerDto;
import com.example.rentService.Producer.CompanyProducer;
import com.example.rentService.Producer.ItemProducer;
import com.example.rentService.Repository.RentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private ItemClient itemClient;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    ItemProducer itemProducer;

    @Autowired
    CompanyProducer companyProducer;

    private static final Logger log = LoggerFactory.getLogger(RentService.class);

    public Rent save(RentDto rentDto) throws Exception {
        log.info("method=save, step=starting, rentDto={}", rentDto);
        ItemDto itemDto = itemClient.getItemById(rentDto.getItemId());
        CompanyDto companyDto = companyClient.getItemById(rentDto.getCompanyId());
        if( companyDto == null|| itemDto == null || itemDto.getAmount() <= rentDto.getAmount()) {
            log.error("method=save, step=Error, rentDto={}", rentDto);
            throw new Exception("Error on Feign Client");
        }
        Rent rentEntity = new Rent();
        rentDto.setTotalPrice(rentDto.getAmount() * itemDto.getPrice());
        BeanUtils.copyProperties(rentDto, rentEntity);
        rentRepository.save(rentEntity);
        log.info("method=save, step=Saved, rentDto={}", rentDto);
        RentProducerDto rentProducerDto = new RentProducerDto();
        BeanUtils.copyProperties(rentDto, rentProducerDto);
        rentProducerDto.setId(rentEntity.getId());
        itemProducer.sendMessage(rentProducerDto);
        log.info("method=save, step=MessageBroker, rentProducerDto={}", rentProducerDto);
        companyProducer.sendMessage(rentProducerDto);
        log.info("method=save, step=MessageBroker, rentProducerDto={}", rentProducerDto);
        return rentEntity;
    }

    public List<Rent> list(){
        log.info("method=list, step=starting");
        return rentRepository.findAll();
    }

    public Rent getById(Long id) throws Exception{
        log.info("method=getById, step=starting");
        Optional<Rent> rentOptional = rentRepository.findById(id);
        if(rentOptional.isPresent()){
            log.info("method=getById, step=Found, rentList={}",  rentOptional.get());
            return rentOptional.get();
        }else {
            log.error("method=getById, step=Error");
            throw new Exception("Request not Found");
        }
    }

    public Rent update(RentDto rentDto, Long id) throws Exception{
        log.info("method=update, step=starting, rentDto={}", rentDto);
        Optional<Rent> rentOptional = rentRepository.findById(id);
        if(rentOptional.isPresent()){
            Rent rentEntity = rentOptional.get();
            BeanUtils.copyProperties(rentDto, rentEntity);
            rentRepository.save(rentEntity);
            log.info("method=save, step=updated, rentEntity={}", rentEntity);
            return rentEntity;
        }else {
            log.error("method=update, step=Error");
            throw new Exception("Request not Found");
        }
    }

    public Rent changeStatus(String status, Long id) throws Exception{
        log.info("method=changeStatus, step=starting, id={}", id);
        Optional<Rent> rentOptional = rentRepository.findById(id);
        if(rentOptional.isPresent()){
            Rent rentEntity = rentOptional.get();
            rentEntity.setStatus(Status.valueOf(status));
            if(status.equals("RETURNED")){
                RentProducerDto rentProducerDto = new RentProducerDto();
                BeanUtils.copyProperties(rentEntity, rentProducerDto);
                rentProducerDto.setId(rentEntity.getId());
                rentProducerDto.setRestore(true);
                itemProducer.sendMessage(rentProducerDto);
            }
            rentRepository.save(rentEntity);
            log.info("method=changeStatus, step=updated, rentEntity={}", rentEntity);
            return rentEntity;
        }else {
            log.error("method=changeStatus, step=Error");
            throw new Exception("Request not Found");
        }
    }

    public void delete(Long id) throws Exception{
        log.info("method=delete, step=starting, id={}", id);
        Optional<Rent> rentOptional = rentRepository.findById(id);
        if(rentOptional.isPresent()){
            rentRepository.deleteById(id);
            log.info("method=delete, step=deleted, id={}", id);
        }else {
            log.error("method=delete, step=Error");
            throw new Exception("Request not Found");
        }
    }
}
