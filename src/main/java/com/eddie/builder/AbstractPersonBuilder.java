package com.eddie.builder;

import com.eddie.exception.GuildSystemException;
import com.eddie.exception.RoleException;
import com.eddie.model.enums.BasicRole;
import com.eddie.model.enums.Role;

public abstract class AbstractPersonBuilder {

    public void roleChecking(Role userRole, Role roleShouldBe) throws GuildSystemException {
        if(!userRole.equals(roleShouldBe)){
            throw new RoleException("accessDenied.roleNotFit." + roleShouldBe.name());
        }
    }

    public void roleChecking(BasicRole userRole, BasicRole roleShouldBe) throws GuildSystemException {
        if(!userRole.equals(roleShouldBe)){
            throw new RoleException("accessDenied.roleNotFit." + roleShouldBe.name() );
        }
    }
}
