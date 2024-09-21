package com.example.companyService.Service;

import static org.mockito.ArgumentMatchers.any;
//
//class RequestServiceTest {
//    @InjectMocks
//    private ItemService itemService;
//
//    @Mock
//    private ItemRepository itemRepository;
//
//    private Item item;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        this.item = new Item();
//        this.item.setId(1L);
//        this.item.setUserId(1L);
//        this.item.setAmount(2);
//        this.item.setStatus(ItemType.CREATED);
//        this.item.setDescription("Mecanico Itaguai");
//        this.item.setNotes("Fix car");
//    }
//
//    @Test
//    void save() throws Exception {
//        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
//        Item itemGet = itemService.getById(1L);
//        assertNotNull(itemGet);
//        assertEquals(itemGet.getId(), item.getId());
//        assertEquals(itemGet.getAmount(), item.getAmount());
//        assertEquals(itemGet.getDescription(), item.getDescription());
//    }
//
//    @Test
//    void getById() throws Exception {
//        when(itemRepository.findById(1L)).thenReturn(Optional.ofNullable(item));
//        Item itemSave = itemService.getById(1L);
//        assertNotNull(item);
//        assertEquals(itemSave.getId(), item.getId());
//        assertEquals(itemSave.getAmount(), item.getAmount());
//        assertEquals(itemSave.getDescription(), item.getDescription());
//    }
//
//    @Test
//    void update() throws Exception {
//        when(itemRepository.save(any(Item.class))).thenReturn(item);
//        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
//        RequestUpdateDto requestUpdateDto = new RequestUpdateDto();
//        BeanUtils.copyProperties(item, requestUpdateDto, "id");
//        requestUpdateDto.setDescription("Test Update");
//        requestUpdateDto.setNotes("Notes Update");
//        itemService.update(requestUpdateDto,1L);
//        assertNotNull(item);
//        assertEquals("Notes Update", item.getNotes());
//        assertEquals("Test Update", item.getDescription());
//    }
//}