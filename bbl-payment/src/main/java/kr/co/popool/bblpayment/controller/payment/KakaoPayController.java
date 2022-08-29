package kr.co.popool.bblpayment.controller.payment;

import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblpayment.domain.dto.payment.KakaoPayDTO;
import kr.co.popool.bblpayment.service.payment.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;


    @PostMapping("/payments/kakao")
    public ResponseFormat<KakaoPayDTO.READY_RESPONSE> requestPayment(@RequestBody KakaoPayDTO.READY_REQUEST requestDTO) {

        KakaoPayDTO.READY_RESPONSE response;

        try {
            response = kakaoPayService.requestPayment(requestDTO);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok(response);
    }

    @GetMapping("/payments/kakao/success")
    public ResponseFormat successPayment(@RequestParam("pg_token") String pgToken) {
        
        //TODO: tid, requestDTO를 어떻게 가져와서 같이 넘겨줄지 

        try {
            kakaoPayService.successPayment(pgToken);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }
}
