package com.rebwon.cases;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class SampleController {

    private final MemberService memberService;
    private final SampleRepository1 sampleRepository1;
    private final SampleRepository2 sampleRepository2;

    @PostMapping("/api/members")
    public ResponseEntity<?> addMember(@RequestBody MemberRequest request) {
        //memberService.addMember2(request.getUsername());
        memberService.addMember(request.getUsername());
        //memberService.addMembers(request.getUsername());
        //return ResponseEntity.ok(URI.create("/api/members/" + member.getId()));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/members/{memberId}")
    public ResponseEntity<?> changeMember(@PathVariable Long memberId,
        @RequestBody MemberChangeRequest request) {
        memberService.updateMember1(memberId, request.getUsername());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/members/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable Long memberId) {
        Member member = memberService.findById1(memberId);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/hello")
    public void hello() {
        //memberService.handle();
        sampleRepository1.handle();
        sampleRepository2.handle();
    }
}
