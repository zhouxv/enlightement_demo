let api = [];
api.push({
    alias: 'debug',
    order: '1',
    desc: '买家售后controller.',
    link: '买家售后_controller.',
    list: []
})
api[0].list.push({
    order: '1',
    desc: '买家发起仲裁',
});
api[0].list.push({
    order: '2',
    desc: '检索所有的仲裁请求',
});
api.push({
    alias: 'sellerAftermarket',
    order: '2',
    desc: '卖家售后controller.',
    link: '卖家售后_controller.',
    list: []
})
api[1].list.push({
    order: '1',
    desc: '检索所有的仲裁请求',
});
api[1].list.push({
    order: '2',
    desc: '卖家仲裁确认',
});
api.push({
    alias: 'DataSetManagement',
    order: '3',
    desc: '用户数据集管理controller.',
    link: '用户数据集管理_controller.',
    list: []
})
api[2].list.push({
    order: '1',
    desc: '数据集所有权声明接口',
});
api[2].list.push({
    order: '2',
    desc: '根据传入的字段检索数据集',
});
api.push({
    alias: 'GoodsManagement',
    order: '4',
    desc: '商品管理controller.',
    link: '商品管理_controller.',
    list: []
})
api[3].list.push({
    order: '1',
    desc: '卖家上架某数据集',
});
api[3].list.push({
    order: '2',
    desc: '商品检索',
});
api[3].list.push({
    order: '3',
    desc: '卖家更新商品信息',
});
api[3].list.push({
    order: '4',
    desc: '删除商品，下架商品',
});
api.push({
    alias: 'ShopController',
    order: '5',
    desc: '商城浏览controller.',
    link: '商城浏览_controller.',
    list: []
})
api[4].list.push({
    order: '1',
    desc: '商城浏览',
});
api[4].list.push({
    order: '2',
    desc: '查询具体的商品信息',
});
api.push({
    alias: 'BuyerTx',
    order: '6',
    desc: '买家交易controller.',
    link: '买家交易_controller.',
    list: []
})
api[5].list.push({
    order: '1',
    desc: '买家开始协商',
});
api[5].list.push({
    order: '2',
    desc: '检索所有的协商请求',
});
api[5].list.push({
    order: '3',
    desc: '买家拿到outerKey',
});
api[5].list.push({
    order: '4',
    desc: '确认outerKey',
});
api[5].list.push({
    order: '5',
    desc: '下载数据集密文包',
});
api[5].list.push({
    order: '6',
    desc: '买家确认数据集密文',
});
api[5].list.push({
    order: '7',
    desc: '下载sample数据集',
});
api[5].list.push({
    order: '8',
    desc: '买家决定交易是否继续进行',
});
api[5].list.push({
    order: '9',
    desc: '买家支付完成调用此接口',
});
api[5].list.push({
    order: '10',
    desc: '下载K2',
});
api[5].list.push({
    order: '11',
    desc: '订单确认',
});
api.push({
    alias: 'SellerTx',
    order: '7',
    desc: '卖家交易controller.',
    link: '卖家交易_controller.',
    list: []
})
api[6].list.push({
    order: '1',
    desc: '卖家检索所有的协商请求',
});
api[6].list.push({
    order: '2',
    desc: '卖家协商',
});
api[6].list.push({
    order: '3',
    desc: '卖家上传OuterKey',
});
api[6].list.push({
    order: '4',
    desc: '卖家上传数据集密文包',
});
api[6].list.push({
    order: '5',
    desc: '卖家上传K2',
});
api.push({
    alias: 'UserController',
    order: '8',
    desc: '用户信息模块controller.',
    link: '用户信息模块_controller.',
    list: []
})
api[7].list.push({
    order: '1',
    desc: '用户注册',
});
api.push({
    alias: 'dict',
    order: '9',
    desc: '数据字典',
    link: 'dict_list',
    list: []
})
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code == 13) {
        const search = document.getElementById('search');
        const searchValue = search.value;
        let searchArr = [];
        for (let i = 0; i < api.length; i++) {
            let apiData = api[i];
            const desc = apiData.desc;
            if (desc.indexOf(searchValue) > -1) {
                searchArr.push({
                    order: apiData.order,
                    desc: apiData.desc,
                    link: apiData.link,
                    alias: apiData.alias,
                    list: apiData.list
                });
            } else {
                let methodList = apiData.list || [];
                let methodListTemp = [];
                for (let j = 0; j < methodList.length; j++) {
                    const methodData = methodList[j];
                    const methodDesc = methodData.desc;
                    if (methodDesc.indexOf(searchValue) > -1) {
                        methodListTemp.push(methodData);
                        break;
                    }
                }
                if (methodListTemp.length > 0) {
                    const data = {
                        order: apiData.order,
                        desc: apiData.desc,
                        alias: apiData.alias,
                        link: apiData.link,
                        list: methodListTemp
                    };
                    searchArr.push(data);
                }
            }
        }
        let html;
        if (searchValue == '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api, liClass, display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchArr, liClass, display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiData, liClass, display) {
    let html = "";
    let doc;
    if (apiData.length > 0) {
        for (let j = 0; j < apiData.length; j++) {
            html += '<li class="' + liClass + '">';
            html += '<a class="dd" href="' + apiData[j].alias + '.html#header">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
            html += '<ul class="sectlevel2" style="' + display + '">';
            doc = apiData[j].list;
            for (let m = 0; m < doc.length; m++) {
                html += '<li><a href="' + apiData[j].alias + '.html#_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + doc[m].desc + '</a> </li>';
            }
            html += '</ul>';
            html += '</li>';
        }
    }
    return html;
}