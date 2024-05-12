<template>
  <el-container>
    <el-aside width="200px"></el-aside>
    <el-main>
      <el-row
        type="flex"
        justify="center"
      >
        <!-- justify 对齐方式 -->
        <el-col
          :span="24"
        >
          <b>服务条款</b>
          <el-divider></el-divider>
        </el-col>
      </el-row>
      <e-row
        type="flex"
        justify="center"
      >
        <!-- justify 对齐方式 -->
        <el-col
          :span="24"
        >
          在您使用包括 notebook.com 在内的 NOTEBook 系统各模块服务（以下统称为“NOTEBook”）之前，请务必认真阅读和理解本《NOTEBook 系统服务条款》（以下简称为“本条款”），并确认您理解了您的权利和限制。如果您不认可本条款，您可以选择不使用 NOTEBook 的服务；一旦您选择使用 NOTEBook 的服务，您的行为将被视为对条款所有内容的认可。
        </el-col>
      </e-row>
      <e-row
        type="flex"
        justify="center"
      >
        <!-- justify 对齐方式 -->
        <el-col
          :span="1"
        >
          .
        </el-col>
        <!-- justify 对齐方式 -->
        <el-col
          :span="23"
        >
          <div style="height: 10px"></div>
          <ul>
            <li>NOTEBook 在中国大陆地区的服务由天津浩然正气科技有限公司运营维护。</li>
            <div style="height: 10px"></div>
            <li>NOTEBook 会记录你的笔记内容，经过算法处理，存放在服务器端，个人隐私将会被严密保护。NOTEBook的账号密码会经过加密算法处理，存放在服务器端的数据库里，所有的网站管理人员都没有权限查看用户的密码。注册账号时会给账号绑定的邮箱发送验证码，这是提高账号安全性的措施之一。您因点击通向第三方内容的链接泄露的一切信息，以及由于访问第三方网页或其他第三方提供的产品而造成的一切后果，NOTEBook 及 NOTEBook 的相关方概不负责，亦不承担任何法律责任。</li>
            <div style="height: 10px"></div>
            <li>NOTEBook 尊重并愿意保护知识产权，如果您认为 NOTEBook 提供的服务或者提供的链接涉嫌侵犯您的相关权益，您应及时向 NOTEBook 相关方提出书面通知，提供您的身份证明、相关权属证明、侵权情况证明以及其他必要的文件。NOTEBook 相关方在收到上述文件后，将会在查证相关侵权情况之后停止对侵权内容的展示以及对侵权的第三方内容的链接的展示。</li>
          </ul>
        </el-col>
      </e-row>
    </el-main>
  </el-container>
</template>

<script>
  export default {
    name: 'Terms',
  }
</script>

<style scoped>

</style>
