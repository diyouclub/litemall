// pages/ucenter/withdrawalsRecord/withdrawalsRecord.js
var util= require('../../../utils/util.js');
var api = require('../../../config/api.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentTab: "0",
    dateStart: util.formatDate(new Date()),
    dateEnd: util.formatDate(new Date()),
    now:util.formatDate(new Date()),
    list:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  switchTab(e) {
    let tab = e.currentTarget.id
    if (tab === 'tableft') {
      this.setData({ currentTab: "0" })
    } else if (tab === 'tabright') {
      this.setData({ currentTab: "1" })
    }
  },
  bindStartChange(e) {
    this.setData({
      dateStart: e.detail.value
    })
  },
  bindEndChange(e) {
    this.setData({
      dateEnd: e.detail.value
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.getList()
  },
  getList() {
    let that = this;
    let params = {
      startTime: this.data.dateStart+' 00:00:00',
      endTime: this.data.dateEnd + ' 23:59:59',
      page:1,
      limit:10
    };
    util.request(api.getIncomeList, params,'GET').then(function (res) {
      if (res.errno === 0) {
        let data = res.data
        that.setData({
          list: data.litemallMoneyApplies,
        });
      }
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