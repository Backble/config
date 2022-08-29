package kr.co.popool.bblpayment.service.payment;

import kr.co.popool.bblpayment.domain.dto.payment.PaymentDTO;

import java.util.List;

public interface PaymentService {

    void requestPayment(PaymentDTO.REQUEST requestDTO) throws Exception;
    List<PaymentDTO.DETAIL> selectMyPaymentList(Long memberId) throws Exception;
}
