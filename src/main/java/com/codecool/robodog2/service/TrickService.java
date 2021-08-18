package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.TrickDAO;
import com.codecool.robodog2.dao.implementation.db.TrickDAOJdbc;
import com.codecool.robodog2.model.Trick;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrickService {

    private final TrickDAO trickDAO;

    public TrickService(TrickDAOJdbc trickDAOJdbc) {
        this.trickDAO = trickDAOJdbc;
    }

    public void addTrick(Trick trick) {
        trickDAO.addTrick(trick);
    }
    public List<Trick> listTricks() {
        return trickDAO.listTricks();
    }
    public Trick getTrick(long id){
        return trickDAO.getTrick(id);
    }
    public void updateTrick(Trick trick, long id){
        trickDAO.updateTrick(trick,id);
    }
    public void deleteTrick(long id){
        trickDAO.deleteTrick(id);
    }
}
