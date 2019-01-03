// pages/ucenter/invitation/invitation.js
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var app = getApp();

Page({
  data: {
    isShow:false,
    winWidth: 0,
    winHeight: 0,
    currentTab: 0,
    angle: 0,
    remind: '加载中',
    user:{
      money:100,
      unit:'元'
    },
    qrcode_url:''
  },
  //页面加载完成函数 
  onReady: function () {
    var that = this;

    var userInfo = wx.getStorageSync('userInfo')
    setTimeout(function () {
      that.setData({
        qrcode_url: userInfo.inviteUrl || ''
      });
    }, 1000);
  },
  onPullDownRefresh: function () {
    wx.showNavigationBarLoading() //在标题栏中显示加载
    setTimeout(function () {
      wx.hideNavigationBarLoading() //完成停止加载
      wx.stopPullDownRefresh() //停止下拉刷新
    }, 1500);
  },
  onLoad: function (options) {
   
 
    //this.requestMyData();
  },
  /** 
     * 滑动切换tab 
     */
  bindChange: function (e) {
    var that = this;
    that.setData({
      currentTab: e.detail.current
    });
  },
  /** 
   * 点击tab切换 
   */
  swichNav: function (e) {
    var that = this;
    if (this.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.current
      })
    }
  },
  showQRCode:function(e){
    var that = this;
    that.setData({
      isShow: true
    });

    console.log(that.data.isShow);
  },
  closeQRCode: function () {
    this.setData({
      isShow: false
    });
  },
  // 请求我的数据
  requestMyData: function () {
    var that = this;
    wx.request({
      url: app.d.ceshiUrl + '&action=user&m=details',
      method: 'post',
      data: {
        openid: app.globalData.userInfo.openid
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var status = res.data.status;
        if (status == 1) {
          var user = res.data.user;
          that.setData({
            user: user,
            list_1: res.data.list_1,
            list_2: res.data.list_2,
          });
        } else {
          wx.showToast({
            title: '非法操作！',
            duration: 2000
          });
        }
      },
      error: function (e) {
        wx.showToast({
          title: '网络异常！',
          duration: 2000
        });
      }
    });
  },
})