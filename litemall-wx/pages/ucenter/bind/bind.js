
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
// pages/ucenter/bind/bind.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    mobile:'',
    password:'',
    password2:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  bindKeyInput(e) {
    this.setData({
      password: e.detail.value
    })
  },
  bindKeyInput1(e) {
    this.setData({
      password2: e.detail.value
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    let that = this;
    util.request(api.userMobile).then(function (res) {
      console.log(res.data);
      if (res.errno === 0) {
        console.log(res.data);
        var info = res.data.info;
        if(info.mobile){
          that.setData({
            mobile: info.mobile
          });
        }else{
          wx.showToast({
            title: '请先绑定手机号码',
            icon: 'none',
            duration: 2000
          })
        }
        
      }
    });
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  formSubmit(e) {
    let that = this;
    if(that.data.mobile==''){
      wx.showToast({
        title: '请先绑定手机号码',
        icon: 'none',
        duration: 2000
      })
      return
    }
    if (that.data.password !== that.data.password2){
      wx.showToast({
        title: '两次输入的密码不一致',
        icon: 'none',
        duration: 2000
      })
      return
    }
    if (that.data.password.length<=5) {
      wx.showToast({
        title: '密码长度须大于6',
        icon: 'none',
        duration: 2000
      })
      return
    }
    util.request(api.bindMobile, {
      mobile: that.data.mobile,
      password: that.data.password
    }, 'POST').then(function (res) {
      if (res.errno === 0) {
        wx.showToast({
          title: '密码设置成功',
          icon: 'success',
          duration: 2000
        })
      }
    });
  },
  formReset() {
    
  }
})