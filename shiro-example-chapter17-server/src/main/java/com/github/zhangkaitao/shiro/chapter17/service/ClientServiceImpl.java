package com.github.zhangkaitao.shiro.chapter17.service;

import com.github.zhangkaitao.shiro.chapter17.dao.ClientDao;
import com.github.zhangkaitao.shiro.chapter17.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-17
 * <p>Version: 1.0
 */
@Transactional
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientDao clientDao;

    @Override
    public Client createClient(Client client) {
        return clientDao.createClient(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientDao.updateClient(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientDao.deleteClient(clientId);
    }

    @Override
    public Client findOne(Long clientId) {
        return clientDao.findOne(clientId);
    }

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Client findByClientId(Long clientId) {
        return clientDao.findByClientId(clientId);
    }
}
