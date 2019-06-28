package com.dbdesign.controller;

import com.dbdesign.controller.assembler.MemberAssembler;
import com.dbdesign.dto.Member;
import com.dbdesign.exception.DAOException;
import com.dbdesign.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/member")
public class MemberController
{
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberAssembler assembler;
    @GetMapping("/all")
    public Resources<Resource<Member>> getAll()
    {
        List<Resource<Member>> members = memberService.getAllMembers().stream().map(assembler::toResource).collect(Collectors.toList());
        return new Resources<>(members,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(MemberController.class).getAll()).withSelfRel());
    }
    @GetMapping("{id}")
    public Resource<Member> getById(@PathVariable Integer id) throws DAOException
    {
        return assembler.toResource(memberService.getMemberById(id));
    }
    @PostMapping("/add")
    public Resource<Member> add(@Valid @RequestBody Member member)
    {
        return assembler.toResource(memberService.addMember(member));
    }
    @PutMapping("/update")
    public Resource<Member> update(@RequestBody Member member)
    {
        return assembler.toResource(memberService.updateMember(member));
    }
}
