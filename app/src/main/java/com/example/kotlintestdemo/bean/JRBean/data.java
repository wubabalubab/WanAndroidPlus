package com.example.kotlintestdemo.bean.JRBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 *  首页文章类 置顶文章也是DatasBean类
 */
    public  class data implements Parcelable {
        /**
         * curPage : 2
         * offset : 20
         * over : false
         * pageCount : 494
         * size : 20
         * total : 9877
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        @Override
        public String toString() {
            return "data{" +
                    "curPage=" + curPage +
                    ", offset=" + offset +
                    ", over=" + over +
                    ", pageCount=" + pageCount +
                    ", size=" + size +
                    ", total=" + total +
                    ", datas=" + datas +
                    '}';
        }

        public static class DatasBean implements Parcelable {
            /**
             * apkLink :
             * audit : 1
             * author : 390057892
             * canEdit : false
             * chapterId : 294
             * chapterName : 完整项目
             * collect : false
             * courseId : 13
             * desc : 一款使用Kt编写的小说阅读器,支持追书、看书。拥有语音朗读、仿真翻页、插画、国际化、黑夜模式等功能，适配了安卓9.0,使用AndroidX控件,对刘海屏等异形屏幕有了很好的支持
             * descMd :
             * envelopePic : https://www.wanandroid.com/blogimgs/0c2f76e3-0af7-4dae-a4d8-98173d24512c.png
             * fresh : false
             * host :
             * id : 16908
             * link : https://www.wanandroid.com/blog/show/2903
             * niceDate : 2021-01-14 23:48
             * niceShareDate : 2021-01-14 23:48
             * origin :
             * prefix :
             * projectLink : https://github.com/390057892/reader
             * publishTime : 1610639337000
             * realSuperChapterId : 293
             * selfVisible : 0
             * shareDate : 1610639337000
             * shareUser :
             * superChapterId : 294
             * superChapterName : 开源项目主Tab
             * tags : [{"name":"项目","url":"/project/list/1?cid=294"}]
             * title : &quot;轻之阅读&quot;网络小说阅读器 Reader
             * type : 0
             * userId : -1
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private int audit;
            private String author;
            private boolean canEdit;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String descMd;
            private String envelopePic;
            private boolean fresh;
            private String host;
            private int id;
            private String link;
            private String niceDate;
            private String niceShareDate;
            private String origin;
            private String prefix;
            private String projectLink;
            private long publishTime;
            private int realSuperChapterId;
            private int selfVisible;
            private long shareDate;
            private String shareUser;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<TagsBean> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public boolean isCanEdit() {
                return canEdit;
            }

            public void setCanEdit(boolean canEdit) {
                this.canEdit = canEdit;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getDescMd() {
                return descMd;
            }

            public void setDescMd(String descMd) {
                this.descMd = descMd;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getNiceShareDate() {
                return niceShareDate;
            }

            public void setNiceShareDate(String niceShareDate) {
                this.niceShareDate = niceShareDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getRealSuperChapterId() {
                return realSuperChapterId;
            }

            public void setRealSuperChapterId(int realSuperChapterId) {
                this.realSuperChapterId = realSuperChapterId;
            }

            public int getSelfVisible() {
                return selfVisible;
            }

            public void setSelfVisible(int selfVisible) {
                this.selfVisible = selfVisible;
            }

            public long getShareDate() {
                return shareDate;
            }

            public void setShareDate(long shareDate) {
                this.shareDate = shareDate;
            }

            public String getShareUser() {
                return shareUser;
            }

            public void setShareUser(String shareUser) {
                this.shareUser = shareUser;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            @Override
            public String toString() {
                return "DatasBean{" +
                        "apkLink='" + apkLink + '\'' +
                        ", audit=" + audit +
                        ", author='" + author + '\'' +
                        ", canEdit=" + canEdit +
                        ", chapterId=" + chapterId +
                        ", chapterName='" + chapterName + '\'' +
                        ", collect=" + collect +
                        ", courseId=" + courseId +
                        ", desc='" + desc + '\'' +
                        ", descMd='" + descMd + '\'' +
                        ", envelopePic='" + envelopePic + '\'' +
                        ", fresh=" + fresh +
                        ", host='" + host + '\'' +
                        ", id=" + id +
                        ", link='" + link + '\'' +
                        ", niceDate='" + niceDate + '\'' +
                        ", niceShareDate='" + niceShareDate + '\'' +
                        ", origin='" + origin + '\'' +
                        ", prefix='" + prefix + '\'' +
                        ", projectLink='" + projectLink + '\'' +
                        ", publishTime=" + publishTime +
                        ", realSuperChapterId=" + realSuperChapterId +
                        ", selfVisible=" + selfVisible +
                        ", shareDate=" + shareDate +
                        ", shareUser='" + shareUser + '\'' +
                        ", superChapterId=" + superChapterId +
                        ", superChapterName='" + superChapterName + '\'' +
                        ", title='" + title + '\'' +
                        ", type=" + type +
                        ", userId=" + userId +
                        ", visible=" + visible +
                        ", zan=" + zan +
                        ", tags=" + tags +
                        '}';
            }

            public static class TagsBean implements Parcelable {
                /**
                 * name : 项目
                 * url : /project/list/1?cid=294
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                @Override
                public String toString() {
                    return "TagsBean{" +
                            "name='" + name + '\'' +
                            ", url='" + url + '\'' +
                            '}';
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.name);
                    dest.writeString(this.url);
                }

                public TagsBean() {
                }

                protected TagsBean(Parcel in) {
                    this.name = in.readString();
                    this.url = in.readString();
                }

                public static final Creator<TagsBean> CREATOR = new Creator<TagsBean>() {
                    @Override
                    public TagsBean createFromParcel(Parcel source) {
                        return new TagsBean(source);
                    }

                    @Override
                    public TagsBean[] newArray(int size) {
                        return new TagsBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.apkLink);
                dest.writeInt(this.audit);
                dest.writeString(this.author);
                dest.writeByte(this.canEdit ? (byte) 1 : (byte) 0);
                dest.writeInt(this.chapterId);
                dest.writeString(this.chapterName);
                dest.writeByte(this.collect ? (byte) 1 : (byte) 0);
                dest.writeInt(this.courseId);
                dest.writeString(this.desc);
                dest.writeString(this.descMd);
                dest.writeString(this.envelopePic);
                dest.writeByte(this.fresh ? (byte) 1 : (byte) 0);
                dest.writeString(this.host);
                dest.writeInt(this.id);
                dest.writeString(this.link);
                dest.writeString(this.niceDate);
                dest.writeString(this.niceShareDate);
                dest.writeString(this.origin);
                dest.writeString(this.prefix);
                dest.writeString(this.projectLink);
                dest.writeLong(this.publishTime);
                dest.writeInt(this.realSuperChapterId);
                dest.writeInt(this.selfVisible);
                dest.writeLong(this.shareDate);
                dest.writeString(this.shareUser);
                dest.writeInt(this.superChapterId);
                dest.writeString(this.superChapterName);
                dest.writeString(this.title);
                dest.writeInt(this.type);
                dest.writeInt(this.userId);
                dest.writeInt(this.visible);
                dest.writeInt(this.zan);
                dest.writeTypedList(this.tags);
//                dest.writeList(this.tags);
            }

            public DatasBean() {
            }

            protected DatasBean(Parcel in) {
                this.apkLink = in.readString();
                this.audit = in.readInt();
                this.author = in.readString();
                this.canEdit = in.readByte() != 0;
                this.chapterId = in.readInt();
                this.chapterName = in.readString();
                this.collect = in.readByte() != 0;
                this.courseId = in.readInt();
                this.desc = in.readString();
                this.descMd = in.readString();
                this.envelopePic = in.readString();
                this.fresh = in.readByte() != 0;
                this.host = in.readString();
                this.id = in.readInt();
                this.link = in.readString();
                this.niceDate = in.readString();
                this.niceShareDate = in.readString();
                this.origin = in.readString();
                this.prefix = in.readString();
                this.projectLink = in.readString();
                this.publishTime = in.readLong();
                this.realSuperChapterId = in.readInt();
                this.selfVisible = in.readInt();
                this.shareDate = in.readLong();
                this.shareUser = in.readString();
                this.superChapterId = in.readInt();
                this.superChapterName = in.readString();
                this.title = in.readString();
                this.type = in.readInt();
                this.userId = in.readInt();
                this.visible = in.readInt();
                this.zan = in.readInt();
                this.tags=in.createTypedArrayList(TagsBean.CREATOR);
//                this.tags = new ArrayList<TagsBean>();
//                in.readList(this.tags, TagsBean.class.getClassLoader());
            }

            public static final Creator<DatasBean> CREATOR = new Creator<DatasBean>() {
                @Override
                public DatasBean createFromParcel(Parcel source) {
                    return new DatasBean(source);
                }

                @Override
                public DatasBean[] newArray(int size) {
                    return new DatasBean[size];
                }
            };
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.curPage);
        dest.writeInt(this.offset);
        dest.writeByte(this.over ? (byte) 1 : (byte) 0);
        dest.writeInt(this.pageCount);
        dest.writeInt(this.size);
        dest.writeInt(this.total);
//        dest.writeList(this.datas);
        dest.writeTypedList(this.datas);
    }

    public data() {
    }

    protected data(Parcel in) {
        this.curPage = in.readInt();
        this.offset = in.readInt();
        this.over = in.readByte() != 0;
        this.pageCount = in.readInt();
        this.size = in.readInt();
        this.total = in.readInt();
        this.datas =in.createTypedArrayList(DatasBean.CREATOR);
//        this.datas = new ArrayList<DatasBean>();
//        in.readList(this.datas, DatasBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<data> CREATOR = new Parcelable.Creator<data>() {
        @Override
        public data createFromParcel(Parcel source) {
            return new data(source);
        }

        @Override
        public data[] newArray(int size) {
            return new data[size];
        }
    };
}

