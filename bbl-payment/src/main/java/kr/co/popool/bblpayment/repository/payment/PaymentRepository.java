package kr.co.popool.bblpayment.repository.payment;

import kr.co.popool.bblpayment.domain.entity.payment.PaymentEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    @EntityGraph(attributePaths = {"item"})
    List<PaymentEntity> findPaymentEntitiesByMemberId(Long memberId);
}
