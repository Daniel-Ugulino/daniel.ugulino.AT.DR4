package com.example.companyService.Service;
import com.example.companyService.Domain.Company;
import com.example.companyService.Dto.CompanyDto;
import com.example.companyService.Dto.RentDto;
import com.example.companyService.Repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public Company save(CompanyDto companyDto){
        Company companyEntity = new Company();
        BeanUtils.copyProperties(companyDto, companyEntity);
        companyRepository.save(companyEntity);
        return companyEntity;
    }

    public Company getById(Long id) throws Exception{
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            return companyOptional.get();
        }else {
            throw new Exception("Company not Found");
        }
    }

    public List<Company> list(){
       return companyRepository.findAll();
    }

    public Company update(CompanyDto companyDto, Long id) throws Exception{
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company companyEntity = companyOptional.get();
            BeanUtils.copyProperties(companyDto, companyEntity);
            companyRepository.save(companyEntity);
            return companyEntity;
        }else {
            throw new Exception("Company not Found");
        }
    }

    public void addOrder(RentDto rentDto) throws Exception{
        Optional<Company> companyOptional = companyRepository.findById(rentDto.getCompanyId());
        if(companyOptional.isPresent()){
            Company companyEntity = companyOptional.get();
            companyEntity.addOrder(rentDto.getId());
            companyRepository.save(companyEntity);
        }else {
            throw new Exception("Company not Found");
        }
    }

    public void delete(Long id) throws Exception{
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            companyRepository.deleteById(id);
        }else {
            throw new Exception("Company not Found");
        }
    }
}
