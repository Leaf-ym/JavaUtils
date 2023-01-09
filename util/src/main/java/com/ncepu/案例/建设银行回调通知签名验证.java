package com.ncepu.案例;

import CCBSign.RSASig;

/**
 * @author wengym
 * @version 1.0
 * @desc TODO
 * @date 2022/3/26 14:34
 */
public class 建设银行回调通知签名验证 {
    public static void test1() {
        String strSrc = "POSID=000000000&BRANCHID=330000000&ORDERID=2004010061&PAYMENT=0.01&CURCODE=01&REMARK1=&REMARK2=&SUCCESS=N";
        String strSign = "5bf88c409a13963286904e8954a4d825108f9b5bb60a8c8e5cfc05355fe4e247c777b521c7d68b8d51968285d51d1a0da0c5bd55e19268949a20dd7bd14f17422e41f3e6f7446d2136e10e796abc8b8a6f752bed5091374551d84d02f185aa3f9b516ac77ca319b06a8269389de6d7f677c619bfc0c89ccbcb125ae6dd7cc646";
        String strPubKey = "30819c300d06092a864886f70d010101050003818a003081860281807d1e98e9c10625239ad9116488accf18a95125c83f5ac52f055be47614087b1bc55f1d475ddb0516b6339f7c2a8fd4def86519087cc6ecd8ea4657a5cef26d84890d00772d216e95d0aba1ea9fd39fb02202c82b71333b104e715da5de65be4cf5b83e3c0ba459777fe83a39485f145fccc94b471981348db5beab735c5889f1020111";
        RSASig rsaSig = new RSASig();
        rsaSig.setPublicKey(strPubKey);

        if (rsaSig.verifySigature(strSign, strSrc)) {
            System.out.println("Sign OK");
        } else {
            System.out.println("Sign failed");
        }
    }

    public static void test2() {
        String strSrc = "POSID=000000000&BRANCHID=110000000&ORDERID=20041031&PAYMENT=0.01&CURCODE=01&REMARK1=ccb&REMARK2=test&SUCCESS=Y";
        String strSign = "43680d00f5097caae18b7af3fc936cc79feb621fb166e25affbb52721e2c5c1e656f030dff46e6f0298ef82cf2fd10b6cef34fb2aa270716c30708aeb1abf0520418449614562e891cd5aede8f83b1dd65f76cc81ad5aabfd4aba409da3523ef8e82a7d19055dbb6d9241171893bf282bf64f239677ecd84abbe55fd855f48f3";
        String strPubKey = "30819f300d06092a864886f70d010101050003818d0030818902818100b466e3a0fa097b57a1bc63c1fd5d97d4ef8d270d538a5aee3d1061f579f02a19cf1543701d94d81f46ce56adb84dca440a7e8f5af40538bb7a88efaf9991ead0fabc63d48fd1f12de658229e30e38ccbd9a631ec9c2d95b8590ea1a01d0931221e062544023a1ed2eb7050853fe56bf8cfd0f18243192d38855a36a87badba790203010001";
        RSASig rsaSig = new RSASig();
        rsaSig.setPublicKey(strPubKey);

        if (rsaSig.verifySigature(strSign, strSrc)) {
            System.out.println("Sign OK");
            System.out.println(strPubKey.substring(0,30));
        } else {
            System.out.println("Sign failed");
        }
    }

    public static void test3() {
        String strSrc = "PPOSID=000000000&BRANCHID=110000000&ORDERID=20041031&PAYMENT=0.01&CURCODE=01&REMARK1=ccb&REMARK2=test&SUCCESS=Y";
        String strSign = "3183a60f887937846008f4ecfea725af5d65ecaefebea828459193343df7d0943f0fa9e44a298cc9a8e335bece72f8bfce8da3975e21fe4ce4d6c96894d5428e05e896b7da03f7519551b8a09bf1286ea48975b3cd49978eefbb628cc98f4f064feb898518dfb783acdd25eb6f5507fc00c16d1ae69d801a8cb970c4b7e0959b";
        String strPubKey = "30819f300d06092a864886f70d010101050003818d0030818902818100d0e57a2ebbc82801980de2ad7101c67dc137432bb6ced45882b8d41cbfec7519ae8bf18b2584ae460d7d437aec069ec907935e4b39c72a6291e43a6a88c3405565357dc23c46b7072e6e50b1da4cd9cfdec616cb6ad43f0b013040307973d63b889e78fdd1389714adec663acefe5c974e513a063ba9acb96f590139b0fc571b0203010001";
        RSASig rsaSig = new RSASig();
        rsaSig.setPublicKey(strPubKey);

        if (rsaSig.verifySigature(strSign, strSrc)) {
            System.out.println("Sign OK");
        } else {
            System.out.println("Sign failed");
        }
    }

    public static void test4() {
        String strSrc = "POSID=000000000&BRANCHID=330000000&ORDERID=120040915091516271800047&PAYMENT=0.01&CURCODE=01&REMARK1=&REMARK2=&SUCCESS=Y";
        String strSign = "2732e323aa1d4f460bed516a79944001fbad2c93bbdb0d22c71066f4a69528c8699da27c13893bfcefea14dabb6f3cfa93e1414e8782124c99ffc8b059aad5f7f543993f28d262147b5206aea8d2b72aa34a256a4a5bb9c90c9aa8d2897eae90b581e7e6091fcfbb7f4885711b75b95ba7982f7519dc166e20d8b8294e4af2ad";
        String strPubKey = "30819c300d06092a864886f70d010101050003818a003081860281807e4caba7c0ff9f593bb03ac8e64fcc76ebdf728b3b54493c3f62c7c94e8663d8505da39b08b00df4320c5a49d54c7774044fcc42937a0fb6a3706f724f872fe5f998cc48eb20875902b4b935e14df77b7aeb9224a5cf2db765b20fe56d8f4d5a9e03ab7943a41a179f8240e5311b3957971921fe9ccb9c24c828e99f91cc33f1020111";
        RSASig rsaSig = new RSASig();
        rsaSig.setPublicKey(strPubKey);

        if (rsaSig.verifySigature(strSign, strSrc)) {
            System.out.println("Sign OK");
        } else {
            System.out.println("Sign failed");
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
}
