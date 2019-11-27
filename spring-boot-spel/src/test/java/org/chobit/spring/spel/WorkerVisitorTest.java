package org.chobit.spring.spel;

import org.chobit.spring.TestBase;
import org.chobit.spring.component.WorkersVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkerVisitorTest extends TestBase {

    @Autowired
    private WorkersVisitor workersVisitor;

    @Test
    public void johnSalary() {
        Assert.assertEquals(35000, workersVisitor.getJohnSalary());
    }

    @Test
    public void georgeSalary() {
        Assert.assertEquals(14000, workersVisitor.getGeorgeSalary());
    }

    @Test
    public void susieSalary() {
        Assert.assertEquals(47000, workersVisitor.getSusieSalary());
    }

    @Test
    public void firstWorker() {
        Assert.assertEquals("John", workersVisitor.getFirstWorker());
    }

    @Test
    public void lastWorker() {
        Assert.assertEquals("George", workersVisitor.getLastWorker());
    }

    @Test
    public void numberOfWorkers() {
        Assert.assertEquals(4, workersVisitor.getNumberOfWorkers());
    }


}
