import canHost from '../../config/interface.js'
import { loginWx, loginWxWithAuth } from '../../config/interface.js'
const app = getApp()
Page({
  data: {    
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    select: false,
    hospital: '红棉社康',
    hospitalValue:0,
    amount:12,
    selDate: '',
    arrangeMents: [
      { date: "2019-01-03", beforeName: "上午", before: "未排班", afterName: "下午", after: "未排班" },
      { date: "2019-01-03", beforeName: "上午", before: "未排班", afterName: "下午", after: "未排班" },
      { date: "2019-01-03", beforeName: "上午", before: "未排班", afterName: "下午", after: "未排班" },
      { date: "2019-01-03", beforeName: "上午", before: "未排班", afterName: "下午", after: "未排班" },
      { date: "2019-01-03", beforeName: "上午", before: "未排班", afterName: "下午", after: "未排班" },
      { date: "2019-01-03", beforeName: "上午", before: "未排班", afterName: "下午", after: "未排班" },
      { date: "2019-01-03", beforeName: "上午", before: "未排班", afterName: "下午", after: "未排班" },
      { date: "2019-01-03", beforeName: "上午", before: "未排班", afterName: "下午", after: "未排班" },
      { date: "2019-01-03", beforeName: "上午", before: "未排班", afterName: "下午", after: "未排班" },
      { date: "2019-01-03", beforeName: "上午", before: "未排班", afterName: "下午", after: "未排班" },
      { date: "2019-01-03", beforeName: "上午", before: "未排班", afterName: "下午", after: "未排班" },
      { date: "2019-01-03", beforeName: "上午", before: "未排班", afterName: "下午", after: "未排班" }
    ]
  },
  bindShowMsg() {
    this.setData({
      select: !this.data.select
    })
  },
  mySelect(e) {
    var name = e.currentTarget.dataset.name
    var value = e.currentTarget.dataset.value
    this.setData({
      hospital: name,
      select: false,
      hospitalValue:value,
      selDate:''
    })
    
    var that = this    
    wx.request({
      url: canHost.miniHost +canHost.futureArrangement+'hospital='+value+'&sessionId=' + wx.getStorageSync("miniSessionId"),
      method: "POST",
      data: {
        hospital: value,
        amount:12
      },
      success: function(result) {
        that.setData({
          arrangeMents: result.data.data
        })
        console.log(result.data.data)

      }
    })

  },
  myDate(e) {
    var date = e.currentTarget.dataset.name
    var value = e.currentTarget.dataset.value
    if (value == "休" || value == "满" || value == "未排班") {
      wx.showToast({
        title: '当前日期不可选',
        duration: 2000
      })
      return
    }
    e.currentTarget.color = 'red'
    this.setData({
      selDate: date
    })
  },
  submitDateMent: function(e) {
    console.log('form发生了submit事件，携带数据为：', e.detail.value);
    var formId = e.detail.formId    
    let {
      userName,
      date,
      idNo,
      mobile,
      hospital
    } = e.detail.value;
    if (!userName || !date||!idNo||!mobile) {
      wx.showToast({
        title: '请填写完整',
        duration: 2000
      })
      return;
    }
    var that=this
    wx.request({
      url: canHost.miniHost +canHost.makeADateUrl+'sessionId=' + wx.getStorageSync("miniSessionId"),
      method: "POST",
      data:{
        workDate:date,
        name:userName,
        identification:idNo,
        mobile:mobile,
        hosptital: hospital,
        formId:formId
      },
      success: function (result) {
        console.log(result)   
        if(0==result.data.code){
          wx.showToast({
            title: '预约成功',
            duration: 2000
          })
        }else{
          wx.showToast({
            title: result.data.msg,
            duration: 5000
          })
        }
        that.onLoad()     
      }
    })
  },
  onLoad: function() {
    console.log('onLoad')
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
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }    
  },
  getFuture:function(that){
    var value = that.data.hospitalValue
    console.log("hospitalValue:" + value)
    console.log("miniSessionId:" + wx.getStorageSync("miniSessionId"))
    var header;
    wx.request({
      url: canHost.miniHost + canHost.futureArrangement + 'sessionId=' + wx.getStorageSync("miniSessionId"),
      method: "POST",
      data: {
        hospital: value,
        amount: 12
      },
      success: function (result) {
        console.log(result)
        that.setData({
          arrangeMents: result.data.data
        })
      }
    })
  },
  onReady: function () {
    
    
  },

  showDialog: function () {
    this.dialog.showDialog();
  },

  confirmEvent: function () {
    this.dialog.hideDialog();
  },

  bindGetUserInfo: function (e) {    
    console.log(e.detail)
    loginWxWithAuth(this, app, e.detail,this.getFuture)    
  },
  onShow: function () {
    //获得dialog组件
    this.dialog = this.selectComponent("#dialog");
    console.log(app.globalData.userInfo)
    if (wx.getStorageSync("miniSessionId")) {
      this.getFuture(this)
    }
    else {
      this.showDialog();
    }
  }

})