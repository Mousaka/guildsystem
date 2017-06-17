package com.eddie.builder;

import com.eddie.model.enums.Role;
import com.eddie.model.interfaces.Person;
import com.eddie.model.interfaces.Player;
import com.eddie.model.pojo.Leader;
import com.eddie.model.pojo.Member;

public class PlayerBuilder {
    private Long id;
    private String name;
    private String email;
    private Role role;

    public PlayerBuilder(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.email = person.getEmail();
        this.role = person.getRole();
    }

    public Player buildPlayer() {
        return buildMember();
    }

    public Leader buildLeader() {
        Leader leader = new Leader();
        leader.setId(this.id);
        leader.setName(this.name);
        leader.setEmail(this.email);
        leader.setRole(this.role);
        return leader;
    }

    public Member buildMember() {
        Member member = new Member();
        member.setId(this.id);
        member.setName(this.name);
        member.setEmail(this.email);
        member.setRole(this.role);
        return member;
    }


}