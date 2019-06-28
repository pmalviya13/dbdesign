package com.dbdesign.controller.assembler;

import com.dbdesign.controller.MemberController;
import com.dbdesign.dto.Member;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class MemberAssembler implements ResourceAssembler<Member, Resource<Member>> {
    @Override
    public Resource<Member> toResource(Member member)
    {
        return  new Resource<>(member,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(MemberController.class).getById(member.getCode())).withSelfRel(),
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(MemberController.class).getAll()).withRel("employees")
        );
    }
}
