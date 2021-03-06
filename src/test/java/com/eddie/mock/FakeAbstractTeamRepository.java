package com.eddie.mock;

import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.TeamType;
import com.eddie.repository.AbstractTeamRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EddieChoCho on 2017/6/27.
 */
public class FakeAbstractTeamRepository implements AbstractTeamRepository {

    private Long id = new Long(0);
    public List<Team> teamList;

    public FakeAbstractTeamRepository() {
        teamList = new ArrayList<>();
    }


    @Override
    public Team save(Team team) {
        id++;
        team.setId(id);
        teamList.add(team);
        return team;
    }

    @Override
    public Team findOne(Long id) {
        Team result = null;
        for(Team team : teamList){
            if(team.getId().equals(id)){
                result = team;
                break;
            }
        }
        return result;
    }

    @Override
    public Team findOneByLeader(User user) {
        Team result = null;
        for(Team team : teamList){
            if(team.getLeader().equals(user)){
                result = team;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Team> findAllByType(TeamType teamType) {
        List<Team> teams = new ArrayList<>();
        for(Team team : teamList){
            if(team.getType().equals(teamType)){
                teams.add(team);
            }
        }
        return teams;
    }

    @Override
    public void delete(Team team) {
        teamList.remove(team);
    }
}
