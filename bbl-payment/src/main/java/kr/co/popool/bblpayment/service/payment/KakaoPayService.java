package kr.co.popool.bblpayment.service.payment;

import kr.co.popool.bblpayment.domain.dto.payment.KakaoPayDTO;
import kr.co.popool.bblpayment.repository.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class KakaoPayService {

    private final KakaoPayClient kakaoPayClient;
    private final PaymentRepository paymentRepository;

    private final String ADMIN_KEY = "KakaoAK c3c1fcb48b30dfb68e37449cc31dffa3";

    public KakaoPayDTO.READY_RESPONSE requestPayment(KakaoPayDTO.READY_REQUEST requestDTO) throws Exception {
        return kakaoPayClient.requestPayForReady(ADMIN_KEY, requestDTO);
    }

    public KakaoPayDTO.APPROVAL_RESPONSE successPayment(String pgToken) throws Exception {
        KakaoPayDTO.APPROVAL_REQUEST requestDTO = new KakaoPayDTO.APPROVAL_REQUEST();
        return kakaoPayClient.requestPayForApproval(ADMIN_KEY, requestDTO);
    }
}
