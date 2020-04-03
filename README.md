# dragon-core

------

- #### Java加密解密技术封装 - Crypto

  -    Base64
  - 摘要算法：MD5/SHA(1/256/384/512)
  - 带密钥摘要算法：HmacMD5/HmacSHA1/HmacSHA256/HmacSHA384/HmacSHA512
  - CRC32
  - 对称加密：DES3/AES
  - 基于口令加密PBE：PBEWithMd5AndDes/PBEWithMd5AndTripleDES/PBEWithSHA1AndDESede/PBEWithSHA1AndRC2_40
  - 非对称加密：RSA

  

- #### 加密型token
  - 解决jwt规范的数据暴露安全问题

  - 可指定加密算法、字节流压缩算法、序列化方案

- #### 弱密码校验通用封装
  - 多种规则支持：长度、字符逻辑相邻、回文、键盘物理键相邻、可控范围内、相邻重复等
  - 多种规则随意组合，通过规则json串生成校验规则，方便动态调整规则