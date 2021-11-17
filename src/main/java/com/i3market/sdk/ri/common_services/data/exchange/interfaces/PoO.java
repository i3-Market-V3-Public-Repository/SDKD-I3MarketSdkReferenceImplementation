package com.i3market.sdk.ri.common_services.data.exchange.interfaces;

public class PoO {

    private String iss;
    private String sub;
    private Long iat;
    private Exchange exchange;

    public static class Exchange {
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getOrig() {
            return orig;
        }

        public void setOrig(String orig) {
            this.orig = orig;
        }

        public String getDest() {
            return dest;
        }

        public void setDest(String dest) {
            this.dest = dest;
        }

        public String getBlock_id() {
            return block_id;
        }

        public void setBlock_id(String block_id) {
            this.block_id = block_id;
        }

        public String getBlock_desc() {
            return block_desc;
        }

        public void setBlock_desc(String block_desc) {
            this.block_desc = block_desc;
        }

        public String getHash_alg() {
            return hash_alg;
        }

        public void setHash_alg(String hash_alg) {
            this.hash_alg = hash_alg;
        }

        public String getCipherblock_dgst() {
            return cipherblock_dgst;
        }

        public void setCipherblock_dgst(String cipherblock_dgst) {
            this.cipherblock_dgst = cipherblock_dgst;
        }

        public String getBlock_commitment() {
            return block_commitment;
        }

        public void setBlock_commitment(String block_commitment) {
            this.block_commitment = block_commitment;
        }

        public String getKey_commitment() {
            return key_commitment;
        }

        public void setKey_commitment(String key_commitment) {
            this.key_commitment = key_commitment;
        }

        private String orig;
        private String dest;
        private String block_id;
        private String block_desc;
        private String hash_alg;
        private String cipherblock_dgst;
        private String block_commitment;
        private String key_commitment;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Long getIat() {
        return iat;
    }

    public void setIat(Long iat) {
        this.iat = iat;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }
}
