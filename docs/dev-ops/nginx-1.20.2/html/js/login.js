document.addEventListener("DOMContentLoaded", () => {
  const loginForm = document.getElementById("loginForm")
  const errorMessage = document.getElementById("errorMessage")

  // 测试账号列表
  const testAccounts = [
    { username: "123456", password: "123456" },
    { username: "test01", password: "123456" },
    { username: "test02", password: "123456" },
    { username: "test03", password: "123456" }
  ]

  loginForm.addEventListener("submit", (e) => {
    e.preventDefault()

    const username = document.getElementById("username").value
    const password = document.getElementById("password").value

    // 验证账号密码
    const isValidUser = testAccounts.some(account => 
      account.username === username && account.password === password
    )

    if (isValidUser) {
      // 登录成功
      errorMessage.style.display = "none"

      // 设置Cookie (有效期为1天)
      const expirationDate = new Date()
      expirationDate.setDate(expirationDate.getDate() + 1)
      document.cookie = `username=${username}; expires=${expirationDate.toUTCString()}; path=/`

      // 确保Cookie设置成功后再跳转
      setTimeout(() => {
        window.location.href = "index.html"
      }, 100)
    } else {
      // 登录失败
      errorMessage.textContent = "账号或密码错误（测试账号：xiaofuge，密码：123456）"
      errorMessage.style.display = "block"
    }
  })
})

