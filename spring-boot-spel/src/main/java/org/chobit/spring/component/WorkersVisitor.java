package org.chobit.spring.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WorkersVisitor {

    @Value("#{workersHolder.salaryByWorkers['John']}") // 35000
    private Integer johnSalary;

    @Value("#{workersHolder.salaryByWorkers['George']}") // 14000
    private Integer georgeSalary;

    @Value("#{workersHolder.salaryByWorkers['Susie']}") // 47000
    private Integer susieSalary;

    @Value("#{workersHolder.workers[0]}") // John
    private String firstWorker;

    @Value("#{workersHolder.workers[3]}") // George
    private String lastWorker;

    @Value("#{workersHolder.workers.size()}") // 4
    private Integer numberOfWorkers;


    public int getJohnSalary() {
        return johnSalary;
    }

    public void setJohnSalary(Integer johnSalary) {
        this.johnSalary = johnSalary;
    }

    public int getGeorgeSalary() {
        return georgeSalary;
    }

    public void setGeorgeSalary(Integer georgeSalary) {
        this.georgeSalary = georgeSalary;
    }

    public int getSusieSalary() {
        return susieSalary;
    }

    public void setSusieSalary(Integer susieSalary) {
        this.susieSalary = susieSalary;
    }

    public String getFirstWorker() {
        return firstWorker;
    }

    public void setFirstWorker(String firstWorker) {
        this.firstWorker = firstWorker;
    }

    public String getLastWorker() {
        return lastWorker;
    }

    public void setLastWorker(String lastWorker) {
        this.lastWorker = lastWorker;
    }

    public int getNumberOfWorkers() {
        return numberOfWorkers;
    }

    public void setNumberOfWorkers(Integer numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }
}
