# dragon-core 聚焦java核心技术

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
  - 长度规则：最大长度、最小长度
  - 相邻顺序规则ABC：顺序个数、是否忽略大小写
  - 回文规则ABCCBA：回文位数，是否忽略大小写
  - 键盘物理键相邻顺序规则：键盘横排连续个数、键盘纵排连续个数
  - 字典规则：字典匹配、是否忽略大小写
  - 正则规则：正则表达式匹配
  - 相邻重复规则AAA：重复个数、是否忽略大小写
  - 多种规则随意组合，支持json字符串的方式创建规则，比如可以从数据库中读取一个json串组成规则方案，方便动态调整规则