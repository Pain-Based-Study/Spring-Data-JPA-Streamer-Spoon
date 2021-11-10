package com.rebwon.cases;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class SampleController {

    private final MemberService memberService;

    @PostMapping("/api/members")
    public ResponseEntity<?> addMember(@RequestBody MemberRequest request) {
        Member member = memberService.addMember(request.getUsername());
        return ResponseEntity.ok(URI.create("/api/members/" + member.getId()));
    }

    @PutMapping("/api/members/{memberId}")
    public ResponseEntity<?> changeMember(@PathVariable Long memberId,
        @RequestBody MemberChangeRequest request) {
        Member member = memberService.updateMember(memberId, request.getUsername());
        return ResponseEntity.ok(member);
    }
}
