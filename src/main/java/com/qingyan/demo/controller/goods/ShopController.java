package com.qingyan.demo.controller.goods;

import com.qingyan.demo.dto.ShopDTO;
import com.qingyan.demo.dto.ShopDetailedDTO;
import com.qingyan.demo.entity.DataSet;
import com.qingyan.demo.entity.Goods;
import com.qingyan.demo.service.daoservice.IDataSetService;
import com.qingyan.demo.service.daoservice.IGoodsService;
import com.qingyan.demo.service.otherservice.FileService;
import com.qingyan.demo.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import static com.qingyan.demo.entity.Goods.goodsListToShopDtoList;

/**
 * 商城浏览 controller.
 *
 * @author zhouxv 2021/7/23.
 */
@Slf4j
@Api(tags = "Shop 商城浏览")
@RequestMapping({"shop"})
@RestController
public class ShopController {
    private final IDataSetService dataSetService;
    private final IGoodsService goodsService;
    private final FileService fileService;

    public ShopController(IDataSetService dataSetService, IGoodsService goodsService, FileService fileService) {
        this.dataSetService = dataSetService;
        this.goodsService = goodsService;
        this.fileService = fileService;
    }

    /**
     * 商城浏览
     *
     * @param contractType 合约类型
     * @return 返回查询List
     * @apiNote 获取所有商品基本信息
     */
    @GetMapping({"getAll"})
    @ApiOperation(value = "商城浏览", notes = "获取所有商品基本信息")
    public ResponseBody<List<ShopDTO>> getAll(Integer contractType) {
        Goods goods = new Goods();
        goods.setContracttype(contractType);
        List<Goods> goodsList = this.goodsService.findAllGoods(goods);
        return ResponseBody.ok("全部商品查询成功", goodsListToShopDtoList(goodsList));
    }

    /**
     * 查询具体的商品信息
     *
     * @param goodsId UUID 商品Id
     * @return 返回指定的商品
     * @apiNote 根据goodsId查询具体的商品信息
     */
    @GetMapping({"getOne"})
    @ApiOperation(value = "根据goodsId查询具体的商品信息", notes = "根据goodsId查询具体的商品信息")
    public ResponseBody getOne(@RequestParam UUID goodsId) {
        Goods goods = this.goodsService.findGoodsById(goodsId.toString());
        if (goods != null) {
            log.info("goodsId为<" + goodsId + ">的商品查询成功");
            DataSet dataSet = this.dataSetService.findDataSetById(goods.getDatasetid());
            return ResponseBody.ok("商品详情查询成功", new ShopDetailedDTO(goods, dataSet));
        }
        log.info("查询不到指定id的商品");
        return ResponseBody.fail("查询不到指定id的商品");
    }

    /**
     * 下载sample数据集
     *
     * @param goodsId UUID
     * @apiNote 买家下载sample数据集
     */
    @GetMapping({"downloadSample"})
    @ApiOperation(value = "下载sample数据集", notes = "买家下载sample数据集")
    public void downloadSampleDataSetup(HttpServletResponse response, @RequestParam UUID goodsId) {
        Goods goods = this.goodsService.findGoodsById(goodsId.toString());
        if (!(goods == null)) {
            DataSet dataSet = this.dataSetService.findDataSetById(goods.getDatasetid());
            this.download(dataSet.getSamplehash(), 0, response);
        } else log.info("非法操作，不存在该商品");
    }


    public void download(String fileName, int fileType, HttpServletResponse response) {
        try {
            File file;
            // path是指想要下载的文件的路径
            if (fileType == 1) {
                file = this.fileService.getDataCipherFile(fileName);
            } else {
                file = this.fileService.getSampleFile(fileName);
            }
            log.info(file.getPath());
            // 获取文件名
            String filename = file.getName();
            // 获取文件后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
