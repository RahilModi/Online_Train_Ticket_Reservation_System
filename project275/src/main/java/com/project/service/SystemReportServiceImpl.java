package com.project.service;

import com.project.dao.SystemReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by kemy on 12/19/17.
 */
@Service
public class SystemReportServiceImpl implements SystemReportService {

    @Autowired
    SystemReportRepository systemReportRepository;

    @Override
    public Map<String,Double> getReservationRate(String date) {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            Date d = f.parse(date);
            List<Object> list = systemReportRepository.getReservationRates(d);
            Map<String, List<List<Integer>>> map = new HashMap<>();

            for(Object b:list) {
                Object[] o = (Object[])b;
                int value = Integer.parseInt(o[3].toString());

                int s = o[1].toString().charAt(0) - 65;
                int e = o[2].toString().charAt(0) - 65;
                int start = (s < e) ? s : e;
                int end = (s < e) ? e : s;
                if(!map.containsKey(o[0].toString())) {
                    List<Integer> list1 = Arrays.asList(start, end, value);
                    map.put(o[0].toString(), new ArrayList<>(Arrays.asList(list1)));
                }
                else{
                    List<List<Integer>> list1 = map.get(o[0].toString());
                    list1.add(Arrays.asList(start, end, value));
                    map.put(o[0].toString(), list1);
                }

            }

            Map<String,List<Integer>> answer = new HashMap<>();
            Map<String,Double> reservationRate = new HashMap<>();
            for (Map.Entry<String, List<List<Integer>>> entry : map.entrySet()) {
                String key = entry.getKey().toString();
                List<List<Integer>> value = entry.getValue();


                List<Integer> result = getModifiedArray(value);

                double rate = 0;
                for(Integer integer:result) {
                    rate += integer;
                }
                rate /= (double)2500;
                answer.put(key,result);
                reservationRate.put(key, rate);
            }
            System.out.println(answer.toString());
            System.out.println(reservationRate.toString());

            return reservationRate;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Map<Date, Double> getDailyReservationRate(String startDate, String endDate) {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            Date sDate = f.parse(startDate);
            Date eDate = f.parse(endDate);
            List<Date> list = systemReportRepository.getDailyReservationRates(sDate, eDate);
            Map<Date, Double> dailyReservationRate = new HashMap<>();
            for(Date date: list) {
                String d=(new StringBuilder()).append(date.toString()).append(" 00:00:00").toString();
                Map<String,Double> reservationRate = getReservationRate(d);
                double dailyRate = 0;
                int count = 0;
                for (Map.Entry<String,Double> entry : reservationRate.entrySet()) {
                    dailyRate += (Double)entry.getValue();
                    count++;
                }
                dailyRate /= count;
                dailyReservationRate.put(date,dailyRate);
            }
            System.out.println(dailyReservationRate.toString());
            return dailyReservationRate;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Integer> getModifiedArray(List<List<Integer>> updates) {

        Integer[] res= new Integer[26];
        Arrays.fill(res,0);
        for(List<Integer> update : updates) {
            Integer value = new Integer(update.get(2));
            Integer start = new Integer(update.get(0));
            Integer end = new Integer(update.get(1));

            res[start] += value;

            if(end < 26 - 1)
                res[end + 1] -= value;

        }

        int sum = 0;
        for(int i = 0; i < 26; i++) {
            sum += res[i];
            res[i] = sum;
        }

        return Arrays.asList(res);
    }

}
