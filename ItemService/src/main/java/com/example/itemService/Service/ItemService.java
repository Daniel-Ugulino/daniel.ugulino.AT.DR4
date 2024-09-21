package com.example.itemService.Service;
import com.example.itemService.Domain.Item;
import com.example.itemService.Dto.ItemDto;
import com.example.itemService.Dto.RentDto;
import com.example.itemService.Repository.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item save(ItemDto itemDto){
        Item itemEntity = new Item();
        BeanUtils.copyProperties(itemDto, itemEntity);
        itemRepository.save(itemEntity);
        return itemEntity;
    }

    public List<Item> list(){
        return itemRepository.findAll();
    }

    public Item getById(Long id) throws Exception{
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(itemOptional.isPresent()){
            return itemOptional.get();
        }else {
            throw new Exception("Request not Found");
        }
    }

    public Item update(ItemDto itemDto, Long id) throws Exception{
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(itemOptional.isPresent()){
            Item itemEntity = itemOptional.get();
            BeanUtils.copyProperties(itemDto, itemEntity);
            itemRepository.save(itemEntity);
            return itemEntity;
        }else {
            throw new Exception("Request not Found");
        }
    }

    public void updateAmount(RentDto rentDto) throws Exception{
        Optional<Item> itemOptional = itemRepository.findById(rentDto.getItemId());
        if(itemOptional.isPresent()){
            Item itemEntity = itemOptional.get();
            System.out.println(itemEntity.getAmount() >= rentDto.getAmount());
            if(itemEntity.getAmount() >= rentDto.getAmount()){
                itemEntity.setAmount(itemEntity.getAmount() - rentDto.getAmount());
            }
            itemRepository.save(itemEntity);
        }else {
            throw new Exception("Error on update");
        }
    }

    public void delete(Long id) throws Exception{
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(itemOptional.isPresent()){
            itemRepository.deleteById(id);
        }else {
            throw new Exception("Request not Found");
        }
    }
}
