environments {
    //本地环境
    local {
        jdbc {//数据库
            ip = InetAddress.getLocalHost().getHostAddress()
            username = 'datamaster'
            password = 'datA123!@#'
        }
    }

    // 开发环境
    dev {
        jdbc {//数据库
            ip = '172.24.8.117'
            username = 'datamaster'
            password = 'datA123!@#'
        }
    }

    //测试环境
    beta {
        jdbc {//数据库
            ip = '172.24.8.117'
            username = 'datamaster'
            password = 'datA123!@#'
        }
    }

    lc10 {
        jdbc {//数据库
            ip = '172.24.8.110'
            username = 'datamaster'
            password = 'datA123!@#'
        }
    }

    lc11_lc12{
        jdbc {//数据库
            ip = '172.24.63.111'
            username = 'datamaster'
            password = 'datA123!@#'
        }
    }

    lc14 {
        jdbc {//数据库
            ip = '172.24.8.114'
            username = 'datamaster'
            password = 'datA123!@#'
        }
    }

    lc15{
        jdbc {//数据库
            ip = '172.24.8.115'
            username = 'datamaster'
            password = 'datA123!@#'
        }
    }

    lc16 {
        jdbc {//数据库
            ip = '172.24.8.116'
            username = 'datamaster'
            password = 'datA123!@#'
        }
    }

    lc17 {
        jdbc {//数据库
            ip = '172.24.8.117'
            username = 'datamaster'
            password = 'datA123!@#'
        }
    }

    db131 {
        jdbc {//数据库
            ip = '172.24.8.131'
            username = 'datamaster'
            password = 'datA123!@#'
        }
    }

    db134 {
        jdbc {//数据库
            ip = '172.24.8.134'
            username = 'datamaster'
            password = 'datA123!@#'
        }
    }

    xinghe {
        jdbc {//数据库
            ip = '10.10.10.84'
            username = 'datamaster'
            password = 'datA123!@#'
        }
    }
}
