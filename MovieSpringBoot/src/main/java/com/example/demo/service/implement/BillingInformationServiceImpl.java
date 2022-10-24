package com.example.demo.service.implement;

import com.example.demo.dto.BillingInformationDto;
import com.example.demo.dto.map.BillingInformationMapper;
import com.example.demo.model.BillingInformation;
import com.example.demo.model.Key.BillingInformationKey;
import com.example.demo.repository.BillingInformationRepository;
import com.example.demo.service.BillingInformationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BillingInformationServiceImpl implements BillingInformationService {
    private final BillingInformationRepository billingInformationRepository;
    private final BillingInformationMapper billingInformationMapper;
    private final int MAX_MONTH = 12;


    @Override
    public Boolean updateBillInformation(BillingInformationDto billingInformationDto) {
        BillingInformationKey billingInformationKey = new BillingInformationKey(
                billingInformationDto.getBillingInformationKey().getAccountId(),
                billingInformationDto.getBillingInformationKey().getMovieId());
        BillingInformation billingInformation = billingInformationRepository.findById(billingInformationKey).orElse(null);
        billingInformation.setStatus(billingInformationDto.getStatus());
        try {
            billingInformationRepository.save(billingInformation);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean checkPay(Integer accountId, Integer movieId) {
        BillingInformationKey billingInformationKey = new BillingInformationKey(accountId, movieId);
        BillingInformation billingInformation = billingInformationRepository.findById(billingInformationKey).orElse(null);
        if (Objects.isNull(billingInformation)) {
            return false;
        } else {
            if (billingInformation.getStatus().equals(1)) {
                return true;
            } else return false;
        }
    }

    @Override
    public void deleteBillByAccount(Integer id) {
        List<BillingInformation> billingInformations = billingInformationRepository.findAll();
        for (BillingInformation billingInformation : billingInformations) {
            if (billingInformation.getBillingInformationKey().getAccountId() == id) {
                billingInformationRepository.deleteBill(billingInformation.getBillingInformationKey().getAccountId(), billingInformation.getBillingInformationKey().getMovieId());
            }
        }
    }

    @Override
    public Boolean checkPromoExitBill(Integer promoId) {
        List<BillingInformation> billingInformations = billingInformationRepository.findAll();
        for (BillingInformation billingInformation : billingInformations) {
            if (billingInformation.getPromotion().getId() == promoId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public BillingInformationDto getBill(BillingInformationKey billingInformationKey) {
        try {
            BillingInformation billingInformation = billingInformationRepository.findById(billingInformationKey).orElse(null);
            return billingInformationMapper.billingInformationToBillingInformationDto(billingInformation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean checkExitPay(Integer accountId, Integer movieId) {
        BillingInformationKey billingInformationKey = new BillingInformationKey(accountId, movieId);
        BillingInformation billingInformation = billingInformationRepository.findById(billingInformationKey).orElse(null);
        if (Objects.isNull(billingInformation)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<BillingInformationDto> getAll() {
        return billingInformationRepository.findAll().stream().map(billingInformation -> {
            return billingInformationMapper.billingInformationToBillingInformationDto(billingInformation);
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean addInfoBill(BillingInformationDto billingInformationDto) {
        BillingInformationKey billingInformationKey = new BillingInformationKey();
        billingInformationKey.setAccountId(billingInformationDto.getBillingInformationKey().getAccountId());
        billingInformationKey.setMovieId(billingInformationDto.getBillingInformationKey().getMovieId());
        BillingInformation billingInformation = billingInformationRepository.findById(billingInformationKey).orElse(null);
        if (Objects.isNull(billingInformation)) {
            try {
                billingInformationRepository.saveNewBill(
                    billingInformationDto.getBillingInformationKey().getAccountId(),
                    billingInformationDto.getBillingInformationKey().getMovieId(),
                    billingInformationDto.getPromotion().getId(),
                    billingInformationDto.getStatus(),
                    billingInformationDto.getPrice(),
                    billingInformationDto.getDate());
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            try {
                billingInformationRepository.updateBill(
                        billingInformationDto.getBillingInformationKey().getAccountId(),
                        billingInformationDto.getBillingInformationKey().getMovieId(),
                        billingInformationDto.getPromotion().getId(),
                        billingInformationDto.getStatus(),
                        billingInformationDto.getPrice(),
                        billingInformationDto.getDate());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    @Override
    public Map<String, Object> getChartByYear() {
        Map<String, Object> obj = new HashMap<>();
        List<String> years = billingInformationRepository.getYears();
        obj.put("labels", years);
        List<Double> data = new ArrayList<>();
        for (String year : years) {
            data.add(billingInformationRepository.getTotalPayByYear(year));
        }
        obj.put("data", data);
        obj.put("total", billingInformationRepository.sumAll());
        return obj;
    }

    @Override
    public Map<String, Object> getChartByMonth(String year) {
        Map<String, Object> obj = new HashMap<>();
        int monthOnYear = checkYear(year);
        List<String> months = createMonth(monthOnYear);
        List<Double> data = new ArrayList<>();
        for (String month : months) {
            Double result = billingInformationRepository.sumByMonthAndYear(year, month);
            data.add(Objects.isNull(result) ? 0.0 : result);
        }
        obj.put("labels", months);
        obj.put("data", data);
        obj.put("total", billingInformationRepository.sumByYear(year));
        return obj;
    }

    @Override
    public List<?> getListYear() {
        return billingInformationRepository.getYears();
    }

    private int checkYear(String year) {
        LocalDate nowDate = LocalDate.now();
        if (year.equals(String.valueOf(LocalDate.now().getYear()))) {
            return nowDate.getMonthValue() + 1;
        } else {
            return MAX_MONTH;
        }
    }

    private List<String> createMonth(int month) {
        List<String> months = new ArrayList<>();
        for (int i = 1; i <= month; i++) {
            months.add(String.valueOf(i));
        }
        return months;
    }
}
