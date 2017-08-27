package com.wangdh.mengm.bean;

import java.io.Serializable;

/**
 * 微信fragment壁纸
 */

public class WechatImage implements Serializable {


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"ret_message":"Success","data":{"title":"耳朵的妙用","img_1920":"http://api.seqier.com/api/bing/img_1920","description":"这些有着巨大耳朵的狐狸属于犬科动物家族，它们的亲戚包括家养狗、豺和狼。这些独特的黑耳朵里面含有大量的血管，有助于调节非洲大草原上的体温。它们的耳朵在捕食时也会派上用场，耳朵贴在地上，大耳狐可以很容易的发现它最喜欢的食物：白蚁。","subtitle":"博茨瓦纳，卡格拉格帝跨境公园","copyright":"卡格拉格帝跨境公园内的两只大耳狐，博茨瓦纳 (© Richard Du Toit/Minden Pictures)","date":"20170826","img_1366":"http://api.seqier.com/api/bing/img_1366"}}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * ret_code : 0
         * ret_message : Success
         * data : {"title":"耳朵的妙用","img_1920":"http://api.seqier.com/api/bing/img_1920","description":"这些有着巨大耳朵的狐狸属于犬科动物家族，它们的亲戚包括家养狗、豺和狼。这些独特的黑耳朵里面含有大量的血管，有助于调节非洲大草原上的体温。它们的耳朵在捕食时也会派上用场，耳朵贴在地上，大耳狐可以很容易的发现它最喜欢的食物：白蚁。","subtitle":"博茨瓦纳，卡格拉格帝跨境公园","copyright":"卡格拉格帝跨境公园内的两只大耳狐，博茨瓦纳 (© Richard Du Toit/Minden Pictures)","date":"20170826","img_1366":"http://api.seqier.com/api/bing/img_1366"}
         */

        private int ret_code;
        private String ret_message;
        private DataBean data;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public String getRet_message() {
            return ret_message;
        }

        public void setRet_message(String ret_message) {
            this.ret_message = ret_message;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * title : 耳朵的妙用
             * img_1920 : http://api.seqier.com/api/bing/img_1920
             * description : 这些有着巨大耳朵的狐狸属于犬科动物家族，它们的亲戚包括家养狗、豺和狼。这些独特的黑耳朵里面含有大量的血管，有助于调节非洲大草原上的体温。它们的耳朵在捕食时也会派上用场，耳朵贴在地上，大耳狐可以很容易的发现它最喜欢的食物：白蚁。
             * subtitle : 博茨瓦纳，卡格拉格帝跨境公园
             * copyright : 卡格拉格帝跨境公园内的两只大耳狐，博茨瓦纳 (© Richard Du Toit/Minden Pictures)
             * date : 20170826
             * img_1366 : http://api.seqier.com/api/bing/img_1366
             */

            private String title;
            private String img_1920;
            private String description;
            private String subtitle;
            private String copyright;
            private String date;
            private String img_1366;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg_1920() {
                return img_1920;
            }

            public void setImg_1920(String img_1920) {
                this.img_1920 = img_1920;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getCopyright() {
                return copyright;
            }

            public void setCopyright(String copyright) {
                this.copyright = copyright;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getImg_1366() {
                return img_1366;
            }

            public void setImg_1366(String img_1366) {
                this.img_1366 = img_1366;
            }
        }
    }
}
