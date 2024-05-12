const ebbinghausWrapper = {
    template:'<div class="content-wrapper">\n' +
    '        <!-- 内容头部 -->\n' +
    '        <section class="content-header">\n' +
    '            <h1>\n' +
    '                艾宾浩斯复盘笔记\n' +
    '            </h1>\n' +
    '            <ol class="breadcrumb">\n' +
    '                <li><a href="#" @click="this.$parent.fun12_2"><i class="fa fa-dashboard"></i>删除</a></li>\n' +
    '            </ol>\n' +
    '        </section>\n' +
    '        <!-- 内容头部 /-->\n' +
    '\n' +
    '        <!-- 正文区域 -->\n' +
    '        <section class="content">\n' +
    '            <div class="row">\n' +
    '                <!--二级侧边导航-->\n' +
    '                <div class="col-md-3">\n' +
    '                    <div class="box box-primary">\n' +
    '                        <!-- sidebar: style can be found in sidebar.less -->\n' +
    '                        <section class="sidebar">\n' +
    '                            <!-- sidebar menu: : style can be found in sidebar.less -->\n' +
    '                            <ul class="sidebar-menu">\n' +
    '                                <!--笔记一-->\n' +
    '                                <li v-for="(ln,index) in this.$parent.labelNotebooks">\n' +
    '                                    <a href="#">\n' +
    '                                        <i class="fa fa-edit"></i> <span @click="funB_1(ln.lnid)">{{ln.labelNotebookName}}</span>\n' +
    '                                    </a>\n' +
    '                                </li>\n' +
    '\n' +
    '                            </ul>\n' +
    '                        </section>\n' +
    '                        <!-- /.sidebar -->\n' +
    '                    </div>\n' +
    '                </div>\n' +
    '\n' +
    '                <!--文本编辑器-->\n' +
    '                <div class="col-md-9">\n' +
    '                    <!--<div>-->\n' +
    '                    <div class="col-md-12">\n' +
    '                        <div class="box box-info">\n' +
    '                            <div class="box-header">\n' +
    '                                <h3 class="box-title">{{this.$parent.labelNotebook.labelNotebookName}}\n' +
    '                                </h3>\n' +
    '                                <!-- tools box -->\n' +
    '                                <div class="pull-right box-tools">\n' +
    '                                    <button type="button" class="btn btn-info btn-sm" data-widget="collapse" data-toggle="tooltip" title="Collapse">\n' +
    '                                        <i class="fa fa-minus"></i></button>\n' +
    '                                </div>\n' +
    '                                <!-- /. tools -->\n' +
    '                            </div>\n' +
    '                            <!-- /.box-header -->\n' +
    '                            <div class="box-body pad">\n' +
    '                                    <textarea id="editor2" name="editor2-textarea" rows="10" cols="80">\n' +
    '                                    </textarea>\n' +
    '                                    <h5 class="center">复盘评分：</h5>\n' +
    '                                    <button class="btn btn-success" @click="$parent.fun13(5)" type="button" value="很好！">Great</button>\n' +
    '                                    <button class="btn btn-warning" @click="$parent.fun13(3)" type="button" value="一般">Not Bad</button>\n' +
    '                                    <button class="btn btn-danger" @click="$parent.fun13(1)" type="button" value="不好">Worse</button>\n' +
    '                            </div>\n' +
    '                        </div>\n' +
    '                        <!-- /.box -->\n' +
    '                    </div>\n' +
    '                    <!--</div>-->\n' +
    '                </div>\n' +
    '            </div>\n' +
    '        </section>\n' +
    '        <!-- 正文区域 /-->\n' +
    '    </div>',
    created(){
        this.$parent.labelNotebook.lid=this.$route.query.lid;
        this.$parent.user.userId = this.$route.query.uid;
    },

    methods:{
        funB_1:function (lnid) {
            this.$parent.labelNotebook.lnid=lnid;
            _this=this;
            if(!CKEDITOR.instances.editor2){
                CKEDITOR.replace('editor2');
                this.$parent.editor2 = CKEDITOR.instances.editor2;
            }
            axios.post('/editor/findLabelNotebookByLnid.do',
                {
                    lnid:lnid,
                })
                .then(function (response) {
                    _this.$parent.labelNotebook.uid = response.data.uid;
                    _this.$parent.labelNotebook.lid = response.data.lid;
                    _this.$parent.labelNotebook.nid = response.data.nid;
                    _this.$parent.labelNotebook.labelNotebookName = response.data.labelNotebookName;
                    _this.$parent.labelNotebook.labelNotebookContent = response.data.labelNotebookContent;
                    //回显笔记内容
                     _this.$parent.editor2.setData(_this.$parent.labelNotebook.labelNotebookContent);
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
};