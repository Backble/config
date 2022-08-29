package kr.co.popool.bblpayment.service.payment;

import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.bblpayment.domain.dto.payment.PaymentDTO;
import kr.co.popool.bblpayment.domain.entity.item.ItemMstEntity;
import kr.co.popool.bblpayment.domain.entity.payment.PaymentEntity;
import kr.co.popool.bblpayment.repository.item.ItemRepository;
import kr.co.popool.bblpayment.repository.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ItemRepository<ItemMstEntity> itemRepository;
    private final KakaoPayClient kakaoPayClient;

    @Override
    @Transactional
    public void requestPayment(PaymentDTO.REQUEST requestDTO) throws Exception {

        ItemMstEntity findItem = itemRepository.findById(requestDTO.getItemId()).orElseThrow(() -> new NotFoundException("Item"));

        PaymentEntity newPayment = PaymentEntity.builder()
                .memberId(requestDTO.getMemberId())
                .item(findItem)
                .quantity(requestDTO.getQuantity())
                .totalAmount(requestDTO.getTotalAmount())
                .build();

        paymentRepository.save(newPayment);
    }

    @Override
    public List<PaymentDTO.DETAIL> selectMyPaymentList(Long memberId) throws Exception {

        List<PaymentEntity> findPaymentEntities = paymentRepository.findPaymentEntitiesByMemberId(memberId);

        List<PaymentDTO.DETAIL> myPaymentList = findPaymentEntities.stream()
                .map(p -> PaymentDTO.DETAIL.builder()
                        .itemName(p.getItem().getName())
                        .itemPrice(p.getItem().getPrice())
                        .totalAmount(p.getTotalAmount())
                        .taxFreeAmount(p.getTaxFreeAmount())
                        .paymentStatus(p.getStatus().getPayment_status())
                        .paymentDate(p.getCreated_at().toString())
                        .quantity(p.getQuantity())
                        .build()
                )
                .collect(Collectors.toList());

        return myPaymentList;
    }
}
