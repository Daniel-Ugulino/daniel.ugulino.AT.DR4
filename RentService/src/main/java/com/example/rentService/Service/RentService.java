package com.example.rentService.Service;
import com.example.rentService.Client.CompanyClient;
import com.example.rentService.Client.ItemClient;
import com.example.rentService.Domain.Rent;
import com.example.rentService.Dto.CompanyDto;
import com.example.rentService.Dto.ItemDto;
import com.example.rentService.Dto.RentDto;
import com.example.rentService.Dto.RentProducerDto;
import com.example.rentService.Producer.CompanyProducer;
import com.example.rentService.Producer.ItemProducer;
import com.example.rentService.Repository.RentRepository;
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

    public Rent save(RentDto rentDto) throws Exception {
        ItemDto itemDto = itemClient.getItemById(rentDto.getItemId());
        CompanyDto companyDto = companyClient.getItemById(rentDto.getCompanyId());
        if( companyDto == null|| itemDto == null || itemDto.getAmount() <= rentDto.getAmount()) {
            throw new Exception("Request not Found");
        }
        Rent rentEntity = new Rent();
        rentDto.setTotalPrice(rentDto.getAmount() * itemDto.getPrice());
        BeanUtils.copyProperties(rentDto, rentEntity);
        rentRepository.save(rentEntity);
        RentProducerDto rentProducerDto = new RentProducerDto();
        BeanUtils.copyProperties(rentDto, rentProducerDto);
        rentProducerDto.setId(rentEntity.getId());
        itemProducer.sendMessage(rentProducerDto);
        companyProducer.sendMessage(rentProducerDto);
        return rentEntity;
    }

    public List<Rent> list(){
        return rentRepository.findAll();
    }

    public Rent getById(Long id) throws Exception{
        Optional<Rent> rentOptional = rentRepository.findById(id);
        if(rentOptional.isPresent()){
            return rentOptional.get();
        }else {
            throw new Exception("Request not Found");
        }
    }

    public Rent update(RentDto rentDto, Long id) throws Exception{
        Optional<Rent> rentOptional = rentRepository.findById(id);
        if(rentOptional.isPresent()){
            Rent rentEntity = rentOptional.get();
            BeanUtils.copyProperties(rentDto, rentEntity);
            rentRepository.save(rentEntity);
            return rentEntity;
        }else {
            throw new Exception("Request not Found");
        }
    }

    public void delete(Long id) throws Exception{
        Optional<Rent> rentOptional = rentRepository.findById(id);
        if(rentOptional.isPresent()){
            rentRepository.deleteById(id);
        }else {
            throw new Exception("Request not Found");
        }
    }
}
