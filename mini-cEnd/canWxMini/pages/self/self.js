import canHost from '../../config/interface.js'
import { loginWx, loginWxWithAuth } from '../../config/interface.js'
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    dateMentInfos: [{ "name": "1111111" }, { "name": "2222222" }]
  },
  //事件处理函数  
  onLoad: function () {
    
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        withCredentials:true,
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
    this.getSelf(this) 
  },
  bindViewEvent: function (e) {
    app.process(this, e)

  },
  getUserInfo: function (e) {
    loginWxWithAuth(this,app,e)
  },
  //获取详情
  getDetail:function(e){
    var value = e.currentTarget.dataset.value
    wx.navigateTo({
      url: '../dateMentDetail/dateMentDetail?current=' + value
    })    
  },
  /**
 * 生命周期函数--监听页面显示
 */
  onShow: function () {    
    this.getSelf(this)    
  },
  getSelf(that){
    wx.request({
      url: canHost.miniHost + canHost.getMyDateMents + 'sessionId=' + wx.getStorageSync("miniSessionId"),
      method: "POST",
      data: {},
      success: function (result) {
        that.setData({
          dateMentInfos: result.data.data.myDates
        })
        console.log(result.data.data)

      }
    })
  }



})
