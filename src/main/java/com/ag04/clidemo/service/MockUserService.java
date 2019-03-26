package com.ag04.clidemo.service;

import com.ag04.clidemo.model.CliUser;
import com.ag04.clidemo.observer.ProgressUpdateEvent;
import org.springframework.stereotype.Service;

import java.util.Observable;
import java.util.Observer;

/**
 * Mock implementation of UserService.
 *
 */
@Service
public class MockUserService extends Observable implements UserService {

    private Observer observer;

    @Override
    public boolean exists(String username) {
        if ("admin".equals(username)) {
            return true;
        }
        return false;
    }

    @Override
    public CliUser create(CliUser user) {
        user.setId(10000L);
        return user;
    }

    @Override
    public CliUser update(CliUser user) {
        return user;
    }

    @Override
    public long updateAll() {
        long numberOfUsers = 2000;
        for (long i = 1; i <= numberOfUsers; i++) {
            if (observer != null) {
                observer.update(this, new ProgressUpdateEvent(i, numberOfUsers));
            }
            // do some operation ...
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return numberOfUsers;
    }

    //--- util methods --------------------------------------------------------

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }
}
