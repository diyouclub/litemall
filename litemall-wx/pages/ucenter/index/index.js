var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var user = require('../../../utils/user.js');
var app = getApp();

Page({
  data: {
    userInfo: {
      nickName: '点击登录',
      avatarUrl: '/static/newimages/lz_05.png',
      inviteUrl:''
    },
    order: {
      unpaid: 0,
      unship: 0,
      unrecv: 0,
      uncomment: 0
    },
    hasLogin: false,
    modalShow:false,
    agency_level:0,
    upUser: {
      mobile: '',
      name: ''
    }
  },
  onLoad: function(options) {
    // 页面初始化 options为页面跳转所带来的参数
  },
  onReady: function() {

  },
  showInviteModal:function(){
    this.setData({
      modalShow: true
    });
  },
  closeQRCode:function(){
    this.setData({
      modalShow: false
    });
  },
  onShow: function() {

    //获取用户的登录信息
    if (app.globalData.hasLogin) {
      let userInfo = wx.getStorageSync('userInfo');
      console.log(userInfo)
      this.setData({
        userInfo: userInfo,
        hasLogin: true,
      });

      let that = this;
      util.request(api.UserIndex).then(function(res) {
        if (res.errno === 0) {
          console.log(res.data);
          that.setData({
            order: res.data.order,
            agency_level: res.data.user.agency_level,
            upUser: res.data.upUser
          });
        }
      });

    }

  },
  onHide: function() {
    // 页面隐藏

  },
  onUnload: function() {
    // 页面关闭
  },
  goLogin() {
    if (!this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }
  },
  goOrder() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/order/order"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }
  },
  goOrderIndex(e) {
    if (this.data.hasLogin) {
      let tab = e.currentTarget.dataset.index
      let route = e.currentTarget.dataset.route
      try {
        wx.setStorageSync('tab', tab);
      } catch (e) {

      }
      wx.navigateTo({
        url: route,
        success: function(res) {},
        fail: function(res) {},
        complete: function(res) {},
      })
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goCoupon() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/couponList/couponList"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goGroupon() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/groupon/myGroupon/myGroupon"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goCollect() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/collect/collect"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goFeedback(e) {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/feedback/feedback"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goFootprint() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/footprint/footprint"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goAddress() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/address/address"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goVip() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/category/category?id=1036010"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goInvite() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/invitation/invitation"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goBind() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/bind/bind"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  bindPhoneNumber: function(e) {
    if (e.detail.errMsg !== "getPhoneNumber:ok") {
      // 拒绝授权
      return;
    }

    if (!this.data.hasLogin) {
      wx.showToast({
        title: '绑定失败：请先登录',
        icon: 'none',
        duration: 2000
      });
      return;
    }

    util.request(api.AuthBindPhone, {
      iv: e.detail.iv,
      encryptedData: e.detail.encryptedData
    }, 'POST').then(function(res) {
      if (res.errno === 0) {
        wx.showToast({
          title: '绑定手机号码成功',
          icon: 'success',
          duration: 2000
        });
      }
    });
  },
  handleContact:function(e){
    console.log(e.path)
    console.log(e.query)
  },
  connactSd: function() {
    let that = this;
    wx.showModal({
      title: '上级用户',
      content: `姓名:${that.data.upUser.name}  电话:${that.data.upUser.mobile}`,
      confirmText:'呼叫',
      success(res) {
        if (res.confirm) {
          wx.makePhoneCall({
            phoneNumber: that.data.upUser.mobile 
          })
        }
      }
    })
  },
  toWithdrawal: function() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/withdrawal/withdrawal"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goAfterSale: function() {
    wx.showToast({
      title: '目前不支持',
      icon: 'none',
      duration: 2000
    });
  },
  aboutUs: function() {
    wx.navigateTo({
      url: '/pages/about/about'
    });
  },
  exitLogin: function() {
    wx.showModal({
      title: '',
      confirmColor: '#b4282d',
      content: '退出登录？',
      success: function(res) {
        if (!res.confirm) {
          return;
        }

        util.request(api.AuthLogout, {}, 'POST');
        app.globalData.hasLogin = false;
        wx.removeStorageSync('token');
        wx.removeStorageSync('userInfo');
        wx.reLaunch({
          url: '/pages/index/index'
        });
      }
    })

  }
})