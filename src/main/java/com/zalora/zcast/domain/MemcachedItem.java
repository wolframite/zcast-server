package com.zalora.zcast.domain;

import lombok.*;
import java.io.Serializable;

import javax.persistence.*;

/**
 * @author Wolfram Huesken <wolfram.huesken@zalora.com>
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "cache")
public class MemcachedItem implements Serializable {

    private static final long serialVersionUID = 7503234879985469265L;

    @Id
    @Getter @Setter
    @Column(name = "id_cache")
    private String key;

    @Getter @Setter
    @Column(name = "data", columnDefinition = "MEDIUMBLOB", nullable = false)
    private byte[] data;

    @Getter @Setter
    @Column(name = "flags", columnDefinition = "SMALLINT DEFAULT 0", nullable = false)
    private long flags;

    @Getter @Setter
    @Column(name = "expire", columnDefinition = "BIGINT DEFAULT 0", nullable = false)
    private long expire;

    public MemcachedItem(String key) {
        this.key = key;
    }

    public MemcachedItem(String key, byte[] data, long flags, long expire) {
        this.key = key;
        this.data = data;
        this.flags = flags;
        this.expire = expire;
    }

}
