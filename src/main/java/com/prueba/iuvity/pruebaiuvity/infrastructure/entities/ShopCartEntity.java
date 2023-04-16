package com.prueba.iuvity.pruebaiuvity.infrastructure.entities;

import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shopCart")
public class ShopCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuarioEntity;
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "produtId", nullable = false)
    private ProductEntity productEntity;
    private int cantidad;
    private double total;
}
