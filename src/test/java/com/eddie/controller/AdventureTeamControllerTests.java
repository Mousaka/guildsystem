package com.eddie.controller;

import com.eddie.builder.PlayerBuilder;
import com.eddie.exception.GuildSystemException;
import com.eddie.mock.FakeAbstractTeamRepository;
import com.eddie.mock.FakeUserRepository;
import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.model.enums.TeamType;
import com.eddie.model.pojo.Leader;
import com.eddie.repository.AbstractUserRepository;
import com.eddie.response.impl.DataResponse;
import com.eddie.service.impl.AdventureTeamServiceImpl;
import com.eddie.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by EddieChoCho on 2017/6/27.
 */
public class AdventureTeamControllerTests {

    private AdventureAbstractTeamController controller;
    private FakeAbstractTeamRepository mockRepository;
    private Leader leader;
    private User member;

    @Before
    public void setUp() throws GuildSystemException {
        AbstractUserRepository userRepository = new FakeUserRepository();
        mockRepository = new FakeAbstractTeamRepository();
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        ObjectMapper mapper = new ObjectMapper();
        DataResponse<Team> response = new DataResponse<>(mapper);
        AdventureTeamServiceImpl teamService = new AdventureTeamServiceImpl(mockRepository);
        controller = new AdventureAbstractTeamController(teamService, userService, response);
        User user = new User("leader", "leader@email", "password", Role.LEADER);
        leader = new PlayerBuilder(user).buildLeader();
        member = new User("member", "member@email", "password", Role.MEMBER);
        userService.add(leader);
        userService.add(member);
    }

    @Test
    public void testCreateTeam() {
        try {
            controller.createTeam(leader, "team", Arrays.asList(member.getId()));
        } catch (GuildSystemException e) {
            e.printStackTrace();
        }
        Team teem =mockRepository.teamList.get(0);
        assert (teem.getLeader().equals(leader));
        assert (teem.getName().equals("team"));
        assert (teem.getMembers().contains(member));
        assert (teem.getType().equals(TeamType.ADVENTURE));
    }

    @Test
    public void testFindTeamLeadedByUser() throws IOException {
        JsonNode node = null;
        Team newTeam = new Team("team",TeamType.ADVENTURE,leader,Arrays.asList(member));
        mockRepository.teamList.add(newTeam);
        try {
            node = controller.findTeamLeadedByUser(leader);
        } catch (GuildSystemException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        assert node != null;
        Team team = mapper.readValue(node.get("data").toString(), Team.class);
        assert (team.getName().equals(newTeam.getName()));
        assert (team.getLeader().getId().equals(newTeam.getLeader().getId()));
        assert (team.getMembers().size() == newTeam.getMembers().size());
        assert (team.getMembers().get(0).getId().equals(newTeam.getMembers().get(0).getId()));
        assert (team.getType().equals(newTeam.getType()));
    }
}