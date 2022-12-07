package com.mustache.hospital.controller;

import com.mustache.hospital.domain.dto.ArticleDto;
import com.mustache.hospital.domain.Article;
import com.mustache.hospital.service.ArticleService;
import com.mustache.hospital.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/hospital")
@Slf4j
public class HospitalController {

    private final ArticleService articleService;
    private final HospitalService hospitalService;

    public HospitalController(ArticleService articleService, HospitalService hospitalService) {
        this.articleService = articleService;
        this.hospitalService = hospitalService;
    }

    @GetMapping(value = "")
    public String index(){
        return "redirect:/hospital/list";
    }

    @GetMapping(value="/list")
    public String list(Model model, @PageableDefault(size = 10, sort = "id") Pageable pageable){
        model.addAttribute("hospitals", hospitalService.getBoardList(pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospita/list";
    }
    @GetMapping(value = "/search")
    public String search(Model model, @PageableDefault(size = 10, sort = "id") Pageable pageable, @RequestParam("keyword") String keyword){
        model.addAttribute("hospitals", hospitalService.getSearchBoardList(keyword, pageable));
        model.addAttribute("keyword", keyword);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospital/list";
    }

    @GetMapping(value = "/board")
    public String boardIndex(){
        return "redirect:/hospital/board/list";
    }

    @GetMapping(value = "/board/list")
    public String boardList(Model model){
        model.addAttribute("board", articleService.getBoardList());
        return "article/list";
    }

    @GetMapping(value = "/board/new")
    public String newArticle(){
        return "article/new";
    }

    @PostMapping(value = "/board/posts")
    public String createArticle(ArticleDto articleDto){
        log.info("title:{} contents:{}", articleDto.getTitle(), articleDto.getContents());
        Article article = articleDto.toEntity();
        articleService.save(article);
        return String.format("redirect:/hospital/board/%d", article.getId());
    }

    @GetMapping(value = "/board/{id}")
    public String selectSingleArticle(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleService.findById(id);
        if(!optArticle.isEmpty()){
            model.addAttribute("board",optArticle.get());
            return "article/show";
        }else{
            return "article/error";
        }
    }

    @GetMapping(value = "/board/{id}/edit")
    public String editArticle(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleService.findById(id);
        if(!optArticle.isEmpty()){
            model.addAttribute("board", optArticle.get());
            return "article/edit";
        }else{
            return "article/error";
        }
    }

    @PostMapping(value = "/board/{id}/update")
    public String updateArticle(@PathVariable Long id, Model model, ArticleDto articleDto){
        log.info("title:{} contents:{}", articleDto.getTitle(), articleDto.getContents());
        Article article = articleDto.toEntity();
        articleService.save(article);
        model.addAttribute("board", article);
        return String.format("redirect:/hospital/board/{id}", article.getId());
    }

    @GetMapping(value = "/board/{id}/delete")
    public String delete(@PathVariable Long id){
        articleService.delete(id);
        return "redirect:/hospital/board/list";
    }

}
