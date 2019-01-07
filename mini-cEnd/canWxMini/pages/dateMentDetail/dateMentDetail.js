import canHost from '../../config/interface.js'
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    dateMent:{}
  },

  submitCancel:function(e){
    let {
      dateMentId
    } = e.detail.value;

    var that=this;
    wx.request({
      url: canHost.miniHost + canHost.cancelDate + 'sessionId=' + wx.getStorageSync("miniSessionId"),
      method: "POST",
      data: {
        datementId: dateMentId        
      },
      success: function (result) {
        console.log(result)
        if (0 == result.data.code) {
          wx.showToast({
            title: '取消成功',
            duration: 2000
          })
        } else {
          wx.showToast({
            title: result.data.msg,
            duration: 5000
          })
        }
        that.onLoad()
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.current)
    this.setData({      
      dateMent: JSON.parse(options.current)
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

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