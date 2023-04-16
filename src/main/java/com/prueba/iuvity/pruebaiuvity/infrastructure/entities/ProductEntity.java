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
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String nombreProduct;
    private String descripcionProduct;
    private int stock;
    private double precio;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarioId",nullable = false)
    private Usuario usuarioEntity;


}
