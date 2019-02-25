// pages/ucenter/withdrawal/withdrawal.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bankCode: '',
    bankName: '',
    money:''
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
      money: e.detail.value
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