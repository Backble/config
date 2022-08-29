package kr.co.popool.bblpayment.controller.payment;

import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblpayment.domain.dto.payment.PaymentDTO;
import kr.co.popool.bblpayment.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping("/payments/request")
    public ResponseFormat requestPayment(@RequestBody PaymentDTO.REQUEST requestDTO) {

        try {
            paymentService.requestPayment(requestDTO);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }

    @GetMapping("/payments/my/{memberId}")
    public ResponseFormat<List<PaymentDTO.DETAIL>> selectMyPaymentList(@PathVariable("memberId") Long memberId) {

        List<PaymentDTO.DETAIL> myPaymentList;

        try {
            myPaymentList = paymentService.selectMyPaymentList(memberId);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok(myPaymentList);
    }

}
