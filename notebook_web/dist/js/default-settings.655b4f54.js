(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["default-settings"],{"36a7":function(t,e,i){},"525c":function(t,e,i){"use strict";i.r(e);var s=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{attrs:{id:"settings-wrapper"}},[i("v-card",{staticClass:"py-2 px-4",staticStyle:{position:"fixed",top:"75px",right:"-35px","border-radius":"8px","z-index":"1"},attrs:{id:"settings",color:"rgba(0, 0, 0, .3)",dark:"",flat:"",link:"","min-width":"100"}},[i("v-icon",{attrs:{large:""}},[t._v(" mdi-cog ")])],1),i("v-menu",{attrs:{"close-on-content-click":!1,activator:"#settings",bottom:"","content-class":"v-settings",left:"","nudge-left":"8","offset-x":"",origin:"top right",transition:"scale-transition"},model:{value:t.menu,callback:function(e){t.menu=e},expression:"menu"}},[i("v-card",{staticClass:"text-center mb-0",attrs:{width:"300"}},[i("v-card-text",[i("strong",{staticClass:"mb-3 d-inline-block"},[t._v("颜色过滤器")]),i("v-item-group",{attrs:{mandatory:""},model:{value:t.color,callback:function(e){t.color=e},expression:"color"}},t._l(t.colors,(function(e){return i("v-item",{key:e,attrs:{value:e},scopedSlots:t._u([{key:"default",fn:function(t){var s=t.active,a=t.toggle;return[i("v-avatar",{staticClass:"v-settings__item mx-1",class:s&&"v-settings__item--active",attrs:{color:e,size:"25"},on:{click:a}})]}}],null,!0)})})),1),i("v-divider",{staticClass:"my-4 secondary"}),i("strong",{staticClass:"mb-3 d-inline-block"},[t._v("侧边栏 背景色")]),i("v-item-group",{attrs:{mandatory:""},model:{value:t.gradient,callback:function(e){t.gradient=e},expression:"gradient"}},t._l(t.gradients,(function(e,s){return i("v-item",{key:e,staticClass:"mx-1",attrs:{value:s},scopedSlots:t._u([{key:"default",fn:function(t){var s=t.active,a=t.toggle;return[i("v-avatar",{staticClass:"v-settings__item",class:s&&"v-settings__item--active",attrs:{color:e,size:"24"},on:{click:a}})]}}],null,!0)})})),1),i("v-divider",{staticClass:"my-4 secondary"}),i("v-row",{attrs:{align:"center","no-gutters":""}},[i("v-col",{attrs:{cols:"auto"}},[t._v(" 黑夜模式 ")]),i("v-spacer"),i("v-col",{attrs:{cols:"auto"}},[i("v-switch",{staticClass:"ma-0 pa-0",attrs:{color:"secondary","hide-details":""},model:{value:t.$vuetify.theme.dark,callback:function(e){t.$set(t.$vuetify.theme,"dark",e)},expression:"$vuetify.theme.dark"}})],1)],1),i("v-divider",{staticClass:"my-4 secondary"}),i("v-row",{attrs:{align:"center","no-gutters":""}},[i("v-col",{attrs:{cols:"auto"}},[t._v(" 收起 侧边栏 ")]),i("v-spacer"),i("v-col",{attrs:{cols:"auto"}},[i("v-switch",{staticClass:"ma-0 pa-0",attrs:{color:"secondary","hide-details":""},model:{value:t.mini,callback:function(e){t.mini=e},expression:"mini"}})],1)],1),i("v-divider",{staticClass:"my-4 secondary"}),i("v-row",{attrs:{align:"center","no-gutters":""}},[i("v-col",{attrs:{cols:"auto"}},[t._v(" 侧边栏 背景图片 ")]),i("v-spacer"),i("v-col",{attrs:{cols:"auto"}},[i("v-switch",{staticClass:"ma-0 pa-0",attrs:{color:"secondary","hide-details":""},model:{value:t.drawerImage,callback:function(e){t.drawerImage=e},expression:"drawerImage"}})],1)],1),i("v-divider",{staticClass:"my-4 secondary"}),i("strong",{staticClass:"mb-3 d-inline-block"},[t._v("图片")]),i("v-card",{attrs:{disabled:!t.drawerImage,flat:""}},[i("v-item-group",{staticClass:"d-flex justify-space-between mb-3",model:{value:t.image,callback:function(e){t.image=e},expression:"image"}},t._l(t.images,(function(e,s){return i("v-item",{key:e,staticClass:"mx-1",attrs:{value:s},scopedSlots:t._u([{key:"default",fn:function(t){var s=t.active,a=t.toggle;return[i("v-sheet",{staticClass:"d-inline-block v-settings__item",class:s&&"v-settings__item--active",on:{click:a}},[i("v-img",{attrs:{src:e,height:"100",width:"50"}})],1)]}}],null,!0)})})),1)],1),i("div",{staticClass:"my-12"})],1)],1)],1)],1)},a=[],n=i("5530"),r=i("a452"),c=i("7ffd"),o={name:"DashboardCoreSettings",mixins:[r["a"]],data:function(){return{color:"#E91E63",colors:["#9C27b0","#00CAE3","#4CAF50","#ff9800","#E91E63","#FF5252"],menu:!1,saveImage:""}},computed:Object(n["a"])(Object(n["a"])(Object(n["a"])({},Object(c["d"])("app",["drawer","drawerImage","mini"])),Object(c["d"])("user",["drawer@gradient","drawer@image"])),Object(c["b"])("user",["images","gradients"])),watch:{color:function(t){this.$vuetify.theme.themes[this.isDark?"dark":"light"].primary=t}}},l=o,d=(i("cfab"),i("2877")),h=i("6544"),u=i.n(h),m=i("8212"),g=i("b0af"),v=i("99d9"),p=i("62ad"),f=i("ce7e"),b=i("132d"),S=i("adda"),y=i("ade3"),_=i("4e82"),w=i("58df"),C=i("d9bd"),k=i("2b0e"),x=k["default"].extend({props:{activeClass:String,value:{required:!1}},data:function(){return{isActive:!1}},methods:{toggle:function(){this.isActive=!this.isActive}},render:function(){return this.$scopedSlots.default?(this.$scopedSlots.default&&(t=this.$scopedSlots.default({active:this.isActive,toggle:this.toggle})),Array.isArray(t)&&1===t.length&&(t=t[0]),t&&!Array.isArray(t)&&t.tag?(t.data=this._b(t.data||{},t.tag,{class:Object(y["a"])({},this.activeClass,this.isActive)}),t):(Object(C["c"])("v-item should only contain a single element",this),t)):(Object(C["c"])("v-item is missing a default scopedSlot",this),null);var t}}),$=Object(w["a"])(x,Object(_["a"])("itemGroup","v-item","v-item-group")).extend({name:"v-item"}),j=i("604c"),O=i("e449"),A=i("0fd9"),z=i("8dd9"),E=i("2fa4"),I=i("15fd"),R=(i("0481"),i("4069"),i("ec29"),i("9d01"),i("fe09")),V=i("c37a"),L=i("c3f0"),W=i("0789"),D=i("490a"),B=i("80d2"),F=["title"],N=R["a"].extend({name:"v-switch",directives:{Touch:L["a"]},props:{inset:Boolean,loading:{type:[Boolean,String],default:!1},flat:{type:Boolean,default:!1}},computed:{classes:function(){return Object(n["a"])(Object(n["a"])({},V["a"].options.computed.classes.call(this)),{},{"v-input--selection-controls v-input--switch":!0,"v-input--switch--flat":this.flat,"v-input--switch--inset":this.inset})},attrs:function(){return{"aria-checked":String(this.isActive),"aria-disabled":String(this.isDisabled),role:"switch"}},validationState:function(){return this.hasError&&this.shouldValidate?"error":this.hasSuccess?"success":null!==this.hasColor?this.computedColor:void 0},switchData:function(){return this.setTextColor(this.loading?void 0:this.validationState,{class:this.themeClasses})}},methods:{genDefaultSlot:function(){return[this.genSwitch(),this.genLabel()]},genSwitch:function(){var t=this.attrs$,e=(t.title,Object(I["a"])(t,F));return this.$createElement("div",{staticClass:"v-input--selection-controls__input"},[this.genInput("checkbox",Object(n["a"])(Object(n["a"])({},this.attrs),e)),this.genRipple(this.setTextColor(this.validationState,{directives:[{name:"touch",value:{left:this.onSwipeLeft,right:this.onSwipeRight}}]})),this.$createElement("div",Object(n["a"])({staticClass:"v-input--switch__track"},this.switchData)),this.$createElement("div",Object(n["a"])({staticClass:"v-input--switch__thumb"},this.switchData),[this.genProgress()])])},genProgress:function(){return this.$createElement(W["b"],{},[!1===this.loading?null:this.$slots.progress||this.$createElement(D["a"],{props:{color:!0===this.loading||""===this.loading?this.color||"primary":this.loading,size:16,width:2,indeterminate:!0}})])},onSwipeLeft:function(){this.isActive&&this.onChange()},onSwipeRight:function(){this.isActive||this.onChange()},onKeydown:function(t){(t.keyCode===B["q"].left&&this.isActive||t.keyCode===B["q"].right&&!this.isActive)&&this.onChange()}}}),P=Object(d["a"])(l,s,a,!1,null,null,null);e["default"]=P.exports;u()(P,{VAvatar:m["a"],VCard:g["a"],VCardText:v["b"],VCol:p["a"],VDivider:f["a"],VIcon:b["a"],VImg:S["a"],VItem:$,VItemGroup:j["b"],VMenu:O["a"],VRow:A["a"],VSheet:z["a"],VSpacer:E["a"],VSwitch:N})},"8efc":function(t,e,i){},"915f":function(t,e,i){},"9d01":function(t,e,i){},adda:function(t,e,i){"use strict";var s=i("53ca"),a=(i("a9e3"),i("a15b"),i("8a79"),i("2ca0"),i("8efc"),i("90a2")),n=(i("36a7"),i("24b2")),r=i("58df"),c=Object(r["a"])(n["a"]).extend({name:"v-responsive",props:{aspectRatio:[String,Number],contentClass:String},computed:{computedAspectRatio:function(){return Number(this.aspectRatio)},aspectStyle:function(){return this.computedAspectRatio?{paddingBottom:1/this.computedAspectRatio*100+"%"}:void 0},__cachedSizer:function(){return this.aspectStyle?this.$createElement("div",{style:this.aspectStyle,staticClass:"v-responsive__sizer"}):[]}},methods:{genContent:function(){return this.$createElement("div",{staticClass:"v-responsive__content",class:this.contentClass},this.$slots.default)}},render:function(t){return t("div",{staticClass:"v-responsive",style:this.measurableStyles,on:this.$listeners},[this.__cachedSizer,this.genContent()])}}),o=c,l=i("7560"),d=i("d9f7"),h=i("d9bd"),u="undefined"!==typeof window&&"IntersectionObserver"in window;e["a"]=Object(r["a"])(o,l["a"]).extend({name:"v-img",directives:{intersect:a["a"]},props:{alt:String,contain:Boolean,eager:Boolean,gradient:String,lazySrc:String,options:{type:Object,default:function(){return{root:void 0,rootMargin:void 0,threshold:void 0}}},position:{type:String,default:"center center"},sizes:String,src:{type:[String,Object],default:""},srcset:String,transition:{type:[Boolean,String],default:"fade-transition"}},data:function(){return{currentSrc:"",image:null,isLoading:!0,calculatedAspectRatio:void 0,naturalWidth:void 0,hasError:!1}},computed:{computedAspectRatio:function(){return Number(this.normalisedSrc.aspect||this.calculatedAspectRatio)},normalisedSrc:function(){return this.src&&"object"===Object(s["a"])(this.src)?{src:this.src.src,srcset:this.srcset||this.src.srcset,lazySrc:this.lazySrc||this.src.lazySrc,aspect:Number(this.aspectRatio||this.src.aspect)}:{src:this.src,srcset:this.srcset,lazySrc:this.lazySrc,aspect:Number(this.aspectRatio||0)}},__cachedImage:function(){if(!(this.normalisedSrc.src||this.normalisedSrc.lazySrc||this.gradient))return[];var t=[],e=this.isLoading?this.normalisedSrc.lazySrc:this.currentSrc;this.gradient&&t.push("linear-gradient(".concat(this.gradient,")")),e&&t.push('url("'.concat(e,'")'));var i=this.$createElement("div",{staticClass:"v-image__image",class:{"v-image__image--preload":this.isLoading,"v-image__image--contain":this.contain,"v-image__image--cover":!this.contain},style:{backgroundImage:t.join(", "),backgroundPosition:this.position},key:+this.isLoading});return this.transition?this.$createElement("transition",{attrs:{name:this.transition,mode:"in-out"}},[i]):i}},watch:{src:function(){this.isLoading?this.loadImage():this.init(void 0,void 0,!0)},"$vuetify.breakpoint.width":"getSrc"},mounted:function(){this.init()},methods:{init:function(t,e,i){if(!u||i||this.eager){if(this.normalisedSrc.lazySrc){var s=new Image;s.src=this.normalisedSrc.lazySrc,this.pollForSize(s,null)}this.normalisedSrc.src&&this.loadImage()}},onLoad:function(){this.getSrc(),this.isLoading=!1,this.$emit("load",this.src),this.image&&(this.normalisedSrc.src.endsWith(".svg")||this.normalisedSrc.src.startsWith("data:image/svg+xml"))&&(this.image.naturalHeight&&this.image.naturalWidth?(this.naturalWidth=this.image.naturalWidth,this.calculatedAspectRatio=this.image.naturalWidth/this.image.naturalHeight):this.calculatedAspectRatio=1)},onError:function(){this.hasError=!0,this.$emit("error",this.src)},getSrc:function(){this.image&&(this.currentSrc=this.image.currentSrc||this.image.src)},loadImage:function(){var t=this,e=new Image;this.image=e,e.onload=function(){e.decode?e.decode().catch((function(e){Object(h["c"])("Failed to decode image, trying to render anyway\n\n"+"src: ".concat(t.normalisedSrc.src)+(e.message?"\nOriginal error: ".concat(e.message):""),t)})).then(t.onLoad):t.onLoad()},e.onerror=this.onError,this.hasError=!1,this.sizes&&(e.sizes=this.sizes),this.normalisedSrc.srcset&&(e.srcset=this.normalisedSrc.srcset),e.src=this.normalisedSrc.src,this.$emit("loadstart",this.normalisedSrc.src),this.aspectRatio||this.pollForSize(e),this.getSrc()},pollForSize:function(t){var e=this,i=arguments.length>1&&void 0!==arguments[1]?arguments[1]:100,s=function s(){var a=t.naturalHeight,n=t.naturalWidth;a||n?(e.naturalWidth=n,e.calculatedAspectRatio=n/a):t.complete||!e.isLoading||e.hasError||null==i||setTimeout(s,i)};s()},genContent:function(){var t=o.options.methods.genContent.call(this);return this.naturalWidth&&this._b(t.data,"div",{style:{width:"".concat(this.naturalWidth,"px")}}),t},__genPlaceholder:function(){if(this.$slots.placeholder){var t=this.isLoading?[this.$createElement("div",{staticClass:"v-image__placeholder"},this.$slots.placeholder)]:[];return this.transition?this.$createElement("transition",{props:{appear:!0,name:this.transition}},t):t[0]}}},render:function(t){var e=o.options.render.call(this,t),i=Object(d["a"])(e.data,{staticClass:"v-image",attrs:{"aria-label":this.alt,role:this.alt?"img":void 0},class:this.themeClasses,directives:u?[{name:"intersect",modifiers:{once:!0},value:{handler:this.init,options:this.options}}]:void 0});return e.children=[this.__cachedSizer,this.__cachedImage,this.__genPlaceholder(),this.genContent()],t(e.tag,i,e.children)}})},cfab:function(t,e,i){"use strict";i("915f")}}]);
//# sourceMappingURL=default-settings.655b4f54.js.map