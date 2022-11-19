package prj.yong.payment.approval.van.kftc.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class KeyDownloadEntity {
    @Id
    private Long id;
}
