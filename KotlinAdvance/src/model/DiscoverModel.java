package model;

import java.util.List;

/**
 * Created by HeYingXin on 2018/1/2.
 */
public class DiscoverModel{

    /**
     * data : [{"id":"13","user_id":"26","type":"2","zans":"0","words":"Ssusjsjj","views":"0","reviews":"0","crtime":"1星期前","address":"沙县小吃(梅山路)","longitude":"117.270053","latitude":"31.84952","is_open":"1","avatar":"http://q.qlogo.cn/qqapp/1106499208/EBB21059A41B4DE915BE10BB4967E631/100","nickname":"空岛","video":"","video_pic":"","zan_status":"0","focus_status":"0","pic":["https://zaocan-oss.myyll.com/OSS/Pic/2017/12/16/1513390901817_67477.jpg"]},{"id":"10","user_id":"26","type":"3","zans":"0","words":"Usususuue","views":"0","reviews":"0","crtime":"1星期前","address":"吉利亚特宴会厅","longitude":"117.26908","latitude":"31.850613","is_open":"1","avatar":"http://q.qlogo.cn/qqapp/1106499208/EBB21059A41B4DE915BE10BB4967E631/100","nickname":"空岛","video":"https://zaocan-oss.myyll.com/OSS/Video/2017/12/15/1513309386200_89051.mp4","video_pic":"https://zaocan-oss.myyll.com/OSS/Video/2017/12/15/1513309386553_59825.jpg","zan_status":"0","focus_status":"0","pic":[]},{"id":"5","user_id":"17","type":"1","zans":"0","words":"实锤","views":"0","reviews":"0","crtime":"1星期前","address":"IFC","longitude":"117.26855","latitude":"31.850312","is_open":"1","avatar":"https://zaocan-oss.myyll.com/OSS/Avatar/2017/10/31/1509443363861_83665.jpg","nickname":"UDKqfX","video":"https://zaocan-oss.myyll.com/OSS/Video/2017/12/15/1513308755572_59318.mp4","video_pic":"https://zaocan-oss.myyll.com/OSS/Video/2017/12/15/1513308755864_27701.jpg","zan_status":"0","focus_status":"0","pic":[]},{"id":"4","user_id":"17","type":"3","zans":"0","words":"实锤","views":"0","reviews":"0","crtime":"1星期前","address":"IFC","longitude":"117.26855","latitude":"31.850312","is_open":"1","avatar":"https://zaocan-oss.myyll.com/OSS/Avatar/2017/10/31/1509443363861_83665.jpg","nickname":"UDKqfX","video":"","video_pic":"","zan_status":"0","focus_status":"0","pic":[]},{"id":"2","user_id":"17","type":"2","zans":"1","words":"实锤","views":"0","reviews":"0","crtime":"2星期前","address":"IFC","longitude":"117.26855","latitude":"31.850312","is_open":"1","avatar":"https://zaocan-oss.myyll.com/OSS/Avatar/2017/10/31/1509443363861_83665.jpg","nickname":"UDKqfX","video":"","video_pic":"","zan_status":"0","focus_status":"0","pic":["https://zaocan-oss.myyll.com/OSS/Video/2017/12/15/1513308924309_76885.jpg","https://zaocan-oss.myyll.com/OSS/Video/2017/12/15/1513308924309_76885.jpg"]},{"id":"1","user_id":"17","type":"3","zans":"0","words":"实锤","views":"0","reviews":"10","crtime":"2星期前","address":"IFC","longitude":"117.26855","latitude":"31.850312","is_open":"1","avatar":"https://zaocan-oss.myyll.com/OSS/Avatar/2017/10/31/1509443363861_83665.jpg","nickname":"UDKqfX","video":"https://zaocan-oss.myyll.com/OSS/Video/2017/12/15/1513308755572_59318.mp4","video_pic":"https://zaocan-oss.myyll.com/OSS/Video/2017/12/15/1513308755864_27701.jpg","zan_status":"0","focus_status":"0","pic":[]}]
     * paginated : {"total":"6","count":6,"more":1}
     */

    private PaginatedBean paginated;
    private List<DataBean> data;
    private WashMachine washMachine;

    public WashMachine getWashMachine() {
        return washMachine;
    }

    public void setWashMachine(WashMachine washMachine) {
        this.washMachine = washMachine;
    }

    public PaginatedBean getPaginated() {
        return paginated;
    }

    public void setPaginated(PaginatedBean paginated) {
        this.paginated = paginated;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PaginatedBean {
        /**
         * total : 6
         * count : 6
         * more : 1
         */

        private String total;
        private int count;
        private int more;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getMore() {
            return more;
        }

        public void setMore(int more) {
            this.more = more;
        }
    }

    public static class DataBean {
        /**
         * id : 13
         * user_id : 26
         * type : 2
         * zans : 0
         * words : Ssusjsjj
         * views : 0
         * reviews : 0
         * crtime : 1星期前
         * address : 沙县小吃(梅山路)
         * longitude : 117.270053
         * latitude : 31.84952
         * is_open : 1
         * avatar : http://q.qlogo.cn/qqapp/1106499208/EBB21059A41B4DE915BE10BB4967E631/100
         * nickname : 空岛
         * video :
         * video_pic :
         * zan_status : 0
         * focus_status : 0
         * pic : ["https://zaocan-oss.myyll.com/OSS/Pic/2017/12/16/1513390901817_67477.jpg"]
         */

        private String id;
        private String user_id;
        private String type;
        private String zans;
        private String words;
        private String views;
        private String reviews;
        private String crtime;
        private String address;
        private String longitude;
        private String latitude;
        private String is_open;
        private String avatar;
        private String nickname;
        private String video;
        private String video_pic;
        private String zan_status;
        private String focus_status;
        private List<String> pic;
        private String share_url;
        private String share_title;
        private String share_img;

        public String getShare_img() {
            return share_img;
        }

        public void setShare_img(String share_img) {
            this.share_img = share_img;
        }

        public String getShare_title() {
            return share_title;
        }

        public void setShare_title(String share_title) {
            this.share_title = share_title;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getZans() {
            return zans;
        }

        public void setZans(String zans) {
            this.zans = zans;
        }

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String getReviews() {
            return reviews;
        }

        public void setReviews(String reviews) {
            this.reviews = reviews;
        }

        public String getCrtime() {
            return crtime;
        }

        public void setCrtime(String crtime) {
            this.crtime = crtime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getIs_open() {
            return is_open;
        }

        public void setIs_open(String is_open) {
            this.is_open = is_open;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getVideo_pic() {
            return video_pic;
        }

        public void setVideo_pic(String video_pic) {
            this.video_pic = video_pic;
        }

        public String getZan_status() {
            return zan_status;
        }

        public void setZan_status(String zan_status) {
            this.zan_status = zan_status;
        }

        public String getFocus_status() {
            return focus_status;
        }

        public void setFocus_status(String focus_status) {
            this.focus_status = focus_status;
        }

        public List<String> getPic() {
            return pic;
        }

        public void setPic(List<String> pic) {
            this.pic = pic;
        }
    }
}