package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.Lockfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

@Service
public class LockfileService {
    @Value("${lockfilePath}")
    private String lockfilePath;

    private final Lockfile lockfile;

    Logger logger = Logger.getLogger(EntitlementsService.class.getName());

    @Autowired
    public LockfileService(Lockfile theLockfile) {
        lockfile = theLockfile;
    };

    public boolean getLockfileContents() {
        String fullLockfilePath = System.getenv("LOCALAPPDATA") + lockfilePath;

        try {
            // read and get lockfile
            BufferedReader reader = new BufferedReader(new FileReader(fullLockfilePath));
            String contents = reader.readLine();

            // get values from lockfile
            String name = contents.split(":")[0];
            String pid = contents.split(":")[1];
            String port = contents.split(":")[2];
            String password = contents.split(":")[3];
            String protocol = contents.split(":")[4];

            // set values from lockfile
            lockfile.setName(name);
            lockfile.setPid(pid);
            lockfile.setPort(port);
            lockfile.setPassword(password);
            lockfile.setEncodedPassword(password);
            lockfile.setProtocol(protocol);
            System.out.println(lockfile);

            reader.close();
            return true;
        } catch (IOException e) {
            logger.warning("Lockfile path attempted: " + fullLockfilePath);
            logger.warning("Error occurred in LockfileService: " + e.getMessage());
            return false;
        }
    }
}
