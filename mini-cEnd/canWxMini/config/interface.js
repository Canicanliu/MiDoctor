//const miniHost ="http://203.195.129.169:8084/minidoctor/"
const miniHost = "https://ghch.org.cn/minidoctor/"
//const miniHost = "http://localhost:8084/minidoctor/"
const futureArrangement ="wx/business/getFutureArrange?"
const miniLogin ="wx/mini/doLogin"
const makeADateUrl ="wx/business/makeADate?"
const getMyDateMents ="wx/business/getMyDatements?"
const dateDetail ="wx/business/getDateDetail?"
const cancelDate ="wx/business/cancelADate?"

export function loginWx(that, callBackFun){
  if (wx.getStorageSync("miniSessionId") == '') {
    wx.login({
      success: function (res) {
        console.log(res.code)
        var code = res.code
        wx.request({
          url: miniHost + miniLogin,
          method: "POST",
          data:{
            code:code
          },
          success: function (result) {
            console.log("login result:"+result)
            wx.setStorageSync("miniSessionId", result.data.data.sessionId)
            callBackFun(that)
          }
        })
      }
    })
  }else{
    callBackFun(that)
  }
}

export function loginWxWithAuth(that, app, e, callBackFun){
  console.log(e)
  app.globalData.userInfo = e.detail.userInfo
  app.globalData.encryptedData = e.detail.encryptedData
  app.globalData.iv = e.detail.iv
  that.setData({
    userInfo: e.detail.userInfo,
    hasUserInfo: false
  })  
  wx.login({
    success: function (res) {
      console.log(res.code)
      var code = res.code
      wx.request({
        url: miniHost + miniLogin,
        method: "POST",
        data: {
          code: code,
          encryptedData: e.detail.encryptedData,
          iv: e.detail.iv
        },
        success: function (result) {          
          console.log("login result:" + result)
          wx.setStorageSync("miniSessionId", result.data.data.sessionId)
          callBackFun(that)
        }
      })
    }
  })
}

export default
{
    miniHost,
    futureArrangement,
    miniLogin,
    makeADateUrl,
    getMyDateMents,
    dateDetail,
    cancelDate,    
}