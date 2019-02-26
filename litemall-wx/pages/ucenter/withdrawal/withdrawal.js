// pages/ucenter/withdrawal/withdrawal.js
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    bankCode: '',
    bankName: '',
    money:'',
    accountId:'',
    balance:'0.00',
    sxf:2,
    sjdz:0,
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
    this.getUserIncome();
  },
  withdrawTap() {
    var pattern = /^([1-9]{1})(\d{14}|\d{18})$/;
    if (!this.data.bankCode || !this.data.bankName || !this.data.money){
      return
    }
    if (!pattern.test(this.data.bankCode)) {
      wx.showToast({
        title: '请输入正确的银行卡号',
        icon: 'none',
        duration: 2000
      })
      return
    }
    if (this.data.money<100){
      wx.showToast({
        title: '提现金额必须大于100元',
        icon: 'none',
        duration: 2000
      })
      return
    }
    if (this.data.money>this.data.balance){
      wx.showToast({
        title: '提现金额必须小于可提现金额',
        icon: 'none',
        duration: 2000
      })
      return
    }
    let params = {
      accountId: this.data.accountId,
      money:this.data.money,
      bank_card: this.data.bankCode,
      band_name: this.data.bankName,
      brokerage: this.data.sxf,
      finally_money: this.data.sjdz,
    };
    let that = this;
    util.request(api.getIncomeApply, params, 'POST').then(function (res) {
      if (res.errno === 0) {
        wx.showToast({
          title: '提现申请成功！',
          icon: 'success',
          duration: 2000
        });
        that.onReady();
      }else{
        wx.showToast({
          title: res.errmsg,
          duration: 2000
        })
      }
    });
    
  },
  getUserIncome() {
    let that = this;
    util.request(api.getUserIncome).then(function (res) {
      if (res.errno === 0) {
        let data = res.data
        console.log(res.data);
        that.setData({
          accountId: data.id,
          balance: data.balance
        });
      }
    });
  },
  bindKeyInput1(e) {
    this.setData({
      bankCode: e.detail.value
    })
  },
  bindKeyInput2(e) {
    this.setData({
      bankName: e.detail.value
    })
  },
  bindKeyInput3(e) {
    this.setData({
      money: e.detail.value,
      sjdz: e.detail.value-2
    })
  },
  goList() {
    wx.navigateTo({
      url: "/pages/ucenter/withdrawalsRecord/withdrawalsRecord"
    });
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

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

  }
})