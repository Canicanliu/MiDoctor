import canHost from '../../config/interface.js'
export function loginWx() {
  if (wx.getStorageSync("miniSessionId") == '') {
    wx.login({
      success: function (res) {
        console.log(res.code)
        var code = res.code
        wx.request({
          url: canHost.miniHost + canHost.miniLogin,
          method: "POST",
          data: {
            code: code
          },
          success: function (result) {
            console.log(result)
            wx.setStorageSync("miniSessionId", result.data.data)
          }
        })
      }
    })
  }
}